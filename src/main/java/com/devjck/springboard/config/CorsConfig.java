package com.devjck.springboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig{

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);                   // 서버가 응답할때 json을 자바스크립트에서 처리할 수 있게 설정
        config.addAllowedOrigin("*");   // 리액트 서버에 해당하는 오리진 허용
        config.addAllowedHeader("*");                       // 모든 헤더 허용
        config.addAllowedMethod("*");                       // 모든 요청 메소드 허용

        source.registerCorsConfiguration("/api/**", config);

        return new CorsFilter(source);
    }
}
