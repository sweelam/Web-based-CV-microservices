package com.web.utils.common.dto;

public class RequestVo {
    private long logId;
    private String requestPath;
    private String requestMethod;
    private String headerToken;
    private String requestHeader;

    public RequestVo() {
    }

    public RequestVo(String requestPath, String requestMethod, String headerToken, String requestHeader) {
        this.headerToken = headerToken;
        this.requestPath = requestPath;
        this.requestMethod = requestMethod;
        this.requestHeader = requestHeader;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getHeaderToken() {
        return headerToken;
    }

    public void setHeaderToken(String headerToken) {
        this.headerToken = headerToken;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
}
