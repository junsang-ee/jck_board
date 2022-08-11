package com.devjck.springboard.config.jwt;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 오버라이드
// 로그인ID(이메일)와 패스워드를 입력 받아 요청 시 이 필터 작동
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    // 인증을 시도하는 메소드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JWTAuthenticationFilter : 로그인 시도중");

        try {
            // 1. username(email, id, nick....), password 받아서 User정보 세팅
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(request.getInputStream(), User.class);

            // UsernamePasswordAuthenticationToken을 통해
            // username, password 값 설정 후 인가되지 않은 상태로 설정
            // password는 스프링 시큐리티에서 자동으로 BCryptPasswordEncoder를 통해 BCrypt 암호화됨
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getMailAddress(), user.getPassword());

            // 2. 정상인지 로그인 시도, authenticationManager로 로그인 시도
            // authenticationManager가 실행되면서
            // PrincipalDetailsService의 loadUserByUsername() 함수가 실행됨
            // loadUserByUsername() 을 통해 해당 이메일과 비밀번호에 일치하는 USER객체를 담은 PrincipalDetails 생성
            // 정상적으로 반환되는 PrincipalDetails가 있으면 인가되도록 설정
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            // 3. PrincipalDetails를 스프링 세션에 담고 (권한 관리를 위해)
            // authentication 객체가 서버 session 영역에 저장되며
            // 리턴의 이유는 권환 관리를 security가 대신 해주기 때문에 편하려고 사용
            // 굳이 JWT 토큰을 사용하면서 세션을 만들 이유가 없음. 권한 처리를 위해 세션에 저장
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            log.info("Logined USER == " + principalDetails.getUser().getName());

            // 인가 절차 후 인가 정보 리턴
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // attemptAuthentication 실행 후 인증이 정상적으로 되었으면 successfulAuthentication 메서드가 실행됨
    // JWT 토큰을 만들어서 request 요청한 사용자에게 JWT 토큰을 response 해줌
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("successfulAuthentication 실행됨 == 인증 완료!");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwtToken = createToken(principalDetails);

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }

    public static String createToken(PrincipalDetails principalDetails) {
        HashMap<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        HashMap<String, Object> payloads = new HashMap<>();
        Date now = new Date();
        now.setTime(now.getTime() + JwtProperties.EXPIRATION_TIME);

        payloads.put("exp", now);
        payloads.put("id", principalDetails.getUser().getUserId());
        payloads.put("email", principalDetails.getUser().getMailAddress());
        payloads.put("name", principalDetails.getUser().getName());
        payloads.put("nickname", principalDetails.getUser().getNickName());


        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, JwtProperties.SECRET.getBytes())
                .compact();

        return jwt;
    }
}
