package com.web.cv.auth.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

/**
 * @auth Sweelam
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationErrorResponse {

    private String message;
    private String devErrorMessage;
    private String errorCode;

    public ApplicationErrorResponse() {
    }

    public ApplicationErrorResponse(String message, String devErrorMessage, String errorCode) {
        this.message = message;
        this.devErrorMessage = devErrorMessage;
        this.errorCode = errorCode;
    }
}
