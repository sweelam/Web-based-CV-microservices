package com.web.utils.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
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
}
