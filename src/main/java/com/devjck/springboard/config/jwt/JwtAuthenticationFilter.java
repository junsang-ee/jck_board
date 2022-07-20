package com.devjck.springboard.config.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 시큐리티에서 UsernamePasswordAuthenticationFilter 오버라이드
// login 요청 시 이 필터 작동
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("JWTAuthenticationFilter : 로그인 시도중");

        // 1. username, password 받아서

        // 2. 정상인지 로그인 시도, authenticationManager로 로그인 시도
        // PrincipalDetailService 호출 후 loadUserByUsername() 함수 실행

        // 3. PrincipalDetails를 스프링 세션에 담고 (권한 관리를 위해)

        // 4. JWT 토큰을 만들어서 응답해줌

        return super.attemptAuthentication(request, response);
    }
}
