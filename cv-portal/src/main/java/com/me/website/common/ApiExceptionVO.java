package com.me.website.common;

public class ApiExceptionVO extends Exception {
    private String errorCode;
    private String message;
    private String devErrorMessage;

    public ApiExceptionVO(String errorCode, String message, String devErrorMessage) {
        this.errorCode = errorCode;
        this.message = message;
        this.devErrorMessage = devErrorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDevErrorMessage() {
        return devErrorMessage;
    }

    public void setDevErrorMessage(String devErrorMessage) {
        this.devErrorMessage = devErrorMessage;
    }
}
