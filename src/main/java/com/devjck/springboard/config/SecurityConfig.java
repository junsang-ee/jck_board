package com.devjck.springboard.config;

import com.devjck.springboard.config.auth.AuthenticationResultHandler;
import com.devjck.springboard.config.jwt.JwtAuthenticationFilter;
import com.devjck.springboard.config.jwt.JwtAuthorizationFilter;
import com.devjck.springboard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CorsFilter corsFilter;
    private final UserRepository userRepository;
    private final AuthenticationResultHandler authenticationResultHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();              // Cross Origin 간 CSRF 비활성화 (리액트, 스프링)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 스프링시큐리티가 생성하지도않고 기존것을 사용하지도 않음
                .and()
                .addFilter(corsFilter)      // Cross Origin 간 CORS 필터 적용
                .formLogin().disable()    // 폼 로그인 사용 X
                .httpBasic().disable()      // Http Basic Auth 기반으로 로그인 인증창 뜨지 않게 설정
                .addFilter(jwtAuthenticationFilter())   // AuthenticationManager
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), userRepository))
                .authorizeRequests()        // 인가 요청 URL 매칭해서 접근 처리
                .antMatchers("/api/mypage/**")  // /api/mypage 이하의 URL은 USER 또는 ADMIN 권한을 가진 회원만 접근 ( 추후 수정 )
                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                .antMatchers("/api/test/write", "/api/test/update/**")
                .access("hasRole('ROLE_ROOKIE') or hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/admin/**")   // /api/admin 이하의 URL은 ADMIN 권한을 가진 회원만 접근 ( 추후 수정 )
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();                  // 그 외 URL은 모든 권한 접근 가능 ( 추후 수정 )

    }

    // Login 인증 URL 주소 변경
    // localhost:8080/login => localhost:8080/api/user/login
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager());
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/user/login");

        jwtAuthenticationFilter.setAuthenticationSuccessHandler(authenticationResultHandler);

        return jwtAuthenticationFilter;
    }

//    @Bean
//    public LoginSuccessHandler loginSuccessHandler() {
//        return new LoginSuccessHandler();
//    }
}