package com.devjck.springboard.config.jwt;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

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
            // authentication 객체가 서버 session 영역에 저장됨.
            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
            System.out.println(principalDetails.getUser().getName());

            // 4. JWT 토큰을 만들어서 응답해줌

            // 인가 절차 후 인가 정보 리턴
            return authentication;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
