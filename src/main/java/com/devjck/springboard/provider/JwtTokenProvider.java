package com.devjck.springboard.provider;


import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JwtTokenProvider {
	@Value("${devjck.jwt.secret}")
	private static String JWT_SECRET;
	
	@Value("${devjck.jwt.expi}")
	private static int JWT_EXPIRATION_MS;
	
	// JWT 토근 생성
	public static String generateToken(Authentication auth) {
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + JWT_EXPIRATION_MS);
		
		return Jwts.builder()
				.setSubject((String) auth.getPrincipal()) 			// 시큐리티 사용자 정보
				.setIssuedAt(new Date())							// 발급 시간
				.setExpiration(expireDate)							// 만료 시간 세팅
				.signWith(SignatureAlgorithm.HS256, JWT_SECRET)		// HMAC SHA256 암호화
				.compact();
	}
	
	// JWT 토큰에서 아이디 추출
	public static String getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(JWT_SECRET)
				.parseClaimsJws(token)
				.getBody();
		
		return claims.getSubject();
	}
}
