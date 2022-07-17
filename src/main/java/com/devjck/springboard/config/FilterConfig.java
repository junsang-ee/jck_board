/*
package com.devjck.springboard.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<JwtFilter> filter() {
        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<JwtFilter>();
        bean.addUrlPatterns("/*");
        bean.setOrder(0);           // 낮은 번호 순으로 필터 우선순위
        return bean;
    }
}
*/