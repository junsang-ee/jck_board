package com.devjck.springboard.config.jwt;

import com.devjck.springboard.config.auth.PrincipalDetails;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

// 시큐리티가 BasicAuthenticationFilter를 가지고 있음
// 권한이나 인증이 필요한 특정 주소를 요청했을 때 위 필터를 무조건 탐
// 권한이나 인증이 필요한 주소가 아니라면 이 필터를 타지 않음
@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private UserRepository userRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    // 인증이나 권한이 필요한 주소 요청이 있을 때 해당 필터를 타게 됨.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String testKey = "thisistestkey";
        super.doFilterInternal(request, response, chain);
        log.info("인증이나 권한이 필요한 주소 요청");

        String jwtHeader = request.getHeader("Authorization");
        log.info("jwtHeader == " + jwtHeader);

        // JWT 토큰을 검증을 해서 정상적인 사용자가 아니면 리턴
        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        // JWT 토큰을 검증해서 정상적인 사용자인지 확인
        String jwtToken = request.getHeader("Authorization").replace("Bearer ", "");
        log.info("JWT Token == " + jwtToken);

        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(testKey.getBytes("UTF-8"))
                    .parseClaimsJws(jwtToken)
                    .getBody();

            Map<String, Object> claimMap = claims;
            System.out.println(claimMap);

            Long test = Long.parseLong(claims.get("id").toString());
            log.info("claims id == " + test);

            // 토큰 정보 정상적인 사용자 값이 있으면
            if(claimMap.get("id") != null && !(claimMap.get("id").equals(""))) {
                User userEntity = userRepository.findById(test).get();
                log.info(userEntity.getName());

                PrincipalDetails principalDetails = new PrincipalDetails(userEntity);

                // JWT 토큰 서명을 통해서 서명이 정상이면 Authentication 객체를 만들어준다.
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        principalDetails
                        , null
                        , principalDetails.getAuthorities());

                // 강제로 시큐리티 세션에 접근하여 Authentication 객체 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);

                // Cannot call sendError() after the response has been committed 에러 발생 확인중
            }

        } catch(ExpiredJwtException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

        chain.doFilter(request, response);
    }
}
