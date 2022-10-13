package com.devjck.springboard.config.tracking.interceptor;

import com.devjck.springboard.domain.tracking.Tracking;
import com.devjck.springboard.domain.user.User;

import com.devjck.springboard.service.tracking.TrackingService;
import com.devjck.springboard.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.val;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@Slf4j
@RequiredArgsConstructor
@Component
public class TrackingInterceptor extends HandlerInterceptorAdapter {

    private final UserService userService;

    private final TrackingService trackingService;

    private final ObjectMapper objectMapper;
    private String startTime;
    private String endTime;
    @Data
    private static class HttpTracking {
        private String method;
        private String uri;
        private Map<String, Collection<String>> headers = new HashMap<>();

        private String body;
        private Integer status;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());
        request.setAttribute("startTime", startTime);

        if (request.getClass().getName().contains("SecurityContextHolderAwareRequestWrapper")) return false;

        log.info("[preHandle] userId :: " + request.getAttribute("userId"));

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("response status: {}", response.getStatus());
        log.info("[postHandler] HTTP : {}, URL : {} ", request.getMethod(), request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        endTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis());

        final ContentCachingRequestWrapper cachingRequest = wrapRequest(request);
        final ContentCachingResponseWrapper cachingResponse  = wrapResponse(response);

        HttpTracking httpTrackingReq = getRequest(cachingRequest);
        HttpTracking httpTrackingRes = getResponse(cachingRequest, cachingResponse);

        if (cachingRequest.getContentType() != null) {
            if (cachingRequest.getContentAsByteArray() != null && cachingRequest.getContentAsByteArray().length != 0){
                log.info("Request Body : {}", objectMapper.readTree(cachingRequest.getContentAsByteArray()));
                httpTrackingReq.body = String.valueOf(objectMapper.readTree(cachingRequest.getContentAsByteArray()));
            }
        }
        if (cachingResponse.getContentType() != null && cachingResponse.getContentType().contains("application/json")) {
            if (cachingResponse.getContentAsByteArray() != null && cachingResponse.getContentAsByteArray().length != 0) {
                log.info("Response Body : {}", objectMapper.readTree(cachingResponse.getContentAsByteArray()));
                httpTrackingRes.body = String.valueOf(objectMapper.readTree(cachingResponse.getContentAsByteArray()));
            }
        }


        log.info("afterCompletion Request :: {}", httpTrackingReq);
        log.info("afterCompletion Response :: {}", httpTrackingRes);

        Enumeration requestHeaderNames = request.getHeaderNames();
        Collection<String> responseHeaderNames = response.getHeaderNames();

        HashMap<String, String> requestHeader = new HashMap<>();

        String requestHeaderKey = "", requestHeaderValue = "";

        while(requestHeaderNames.hasMoreElements()) {
            requestHeaderKey = (String) requestHeaderNames.nextElement();
            requestHeaderValue = request.getHeader(requestHeaderKey);
            log.info("[afterCompletion] requestHeader ::::   " + requestHeaderKey + " : " + requestHeaderValue);
            requestHeader.put(requestHeaderKey, requestHeaderValue);
        }


        Long userId = Long.valueOf((String) request.getAttribute("userId"));
        saveTracking(userId, httpTrackingReq, httpTrackingRes);
        User requestUser = userService.findUserByIdTest(userId);
        String httpMethodType = request.getMethod();
        String requestApiUri = request.getRequestURI();
        int resultCode = response.getStatus();

    }

    private void saveTracking(Long userId, HttpTracking httpTrackingReq, HttpTracking httpTrackingRes) {
        User requestUser = userService.findUserByIdTest(userId);
        String httpMethodType = httpTrackingReq.getMethod();
        String requestApiUri = httpTrackingReq.getUri();
        String requestHeader = httpTrackingReq.getHeaders().toString();
        String requestBody = httpTrackingReq.getBody();
        String responseHeader = httpTrackingRes.getHeaders().toString();
        String responseBody = httpTrackingRes.getBody();
        int resultCode = httpTrackingRes.getStatus();

        Tracking tracking = new Tracking(requestUser, httpMethodType, requestApiUri, requestHeader, requestBody,
                responseHeader, responseBody, resultCode, startTime, endTime);

        trackingService.save(tracking);
        log.info("saveTracking :: " + httpTrackingReq);
        log.info("saveTracking :: " + httpTrackingRes);
    }

    private HttpTracking getRequest(ContentCachingRequestWrapper request) throws IOException {
        log.info("getRequest :: ");
        HttpTracking data = getHttpInfo(request);

        Collections.list(request.getHeaderNames()).forEach(headerName ->
                data.headers.put(headerName, Collections.list(request.getHeaders(headerName))));

        return data;
    }

    private HttpTracking getResponse(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) throws IOException {
        HttpTracking data = getHttpInfo(request);
        data.status = response.getStatus();

        response.getHeaderNames().forEach(headerName ->
                data.headers.put(headerName, response.getHeaders(headerName)));

        return data;
    }

    private HttpTracking getHttpInfo(ContentCachingRequestWrapper request) {
        val data = new HttpTracking();

        data.method = request.getMethod();

        String queryString = request.getQueryString();
        if (queryString == null) {
            data.uri = request.getRequestURI();
        } else {
            data.uri = request.getRequestURI() + "?" + queryString;
        }

        return data;
    }

    private ContentCachingRequestWrapper wrapRequest(HttpServletRequest request) {
        if (request instanceof ContentCachingRequestWrapper) {
            return (ContentCachingRequestWrapper) request;
        } else {
            return new ContentCachingRequestWrapper(request);
        }
    }

    private ContentCachingResponseWrapper wrapResponse(HttpServletResponse response) {
        if (response instanceof ContentCachingResponseWrapper) {
            return (ContentCachingResponseWrapper) response;
        } else {
            return new ContentCachingResponseWrapper(response);
        }
    }


}
