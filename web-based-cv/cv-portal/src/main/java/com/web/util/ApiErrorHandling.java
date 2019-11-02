package com.web.util;

import java.util.Arrays;
import java.util.List;

public class ApiErrorHandling extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String message;
    private Integer errorCode;
    private List<String> error;

    public ApiErrorHandling() {
    }

    public ApiErrorHandling(Integer errorCode, String message, List<String> error) {
        this.errorCode = errorCode;
        this.message = message;
        this.error = error;
    }

    public ApiErrorHandling(Integer errorCode, String message, String error) {
        this.errorCode = errorCode;
        this.message = message;
        this.error = Arrays.asList(error);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }
}
