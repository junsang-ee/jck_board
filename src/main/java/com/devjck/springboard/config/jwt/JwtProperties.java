package com.devjck.springboard.config.jwt;

public interface JwtProperties {
    String SECRET = "test";
    Long EXPIRATION_TIME = 1000 * 60 * 60 * 2l;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";

}
