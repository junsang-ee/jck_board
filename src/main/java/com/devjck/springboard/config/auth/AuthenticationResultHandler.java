package com.devjck.springboard.config.auth;

import com.devjck.springboard.config.jwt.JwtProperties;
import com.devjck.springboard.domain.user.User;
import com.devjck.springboard.domain.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationResultHandler extends BasicAuthenticationEntryPoint implements AuthenticationSuccessHandler, AuthenticationFailureHandler {

    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String jwtToken = createToken(principalDetails);
        User user = principalDetails.getUser();
        Long userId = user.getUserId();
        log.info("jwtToken : " + jwtToken);
        userRepository.updateLastAccessTime(LocalDateTime.now(), userId);
        log.info("now : " + LocalDateTime.now());
        response.addHeader("Authorization", "Bearer " + jwtToken);

    }


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.info("fail!!!!!!!!!!!!!!!!!!!!!!!!");
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
