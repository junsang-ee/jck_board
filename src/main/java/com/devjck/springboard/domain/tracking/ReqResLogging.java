package com.devjck.springboard.domain.tracking;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class ReqResLogging {
    private String traceId;
    private String className;
    private String httpMethod;
    private String uri;
    private String method;
    private Map<String, String> params;
    private String logTime;
    private String serverIp;
    private String deviceType;
    private Object requestBody;
    private Object responseBody;
    private String delapsedTime;
    public ReqResLogging(String traceId, String className, String httpMethod,
                         String uri, String method, Map<String, String> params, String logTime,
                         String serverIp, String deviceType) {
        this.traceId = traceId;
        this.className = className;
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.method = method;
        this.params = params;
        this.logTime = logTime;
        this.serverIp = serverIp;
        this.deviceType = deviceType;
//        this.requestBody = requestBody;
    }

}
