package com.web.utils.common.dto;

/**
 * @auth Sweelam
 */
public class ApplicationErrorResponse {

    private String message;
    private String devErrorMessage;
    private String errorCode;

    public ApplicationErrorResponse() {
    }

    /**
     *
     * @param message
     * @param devErrorMessage
     * @param errorCode
     */
    public ApplicationErrorResponse(String message, String devErrorMessage, String errorCode) {
        this.message = message;
        this.devErrorMessage = devErrorMessage;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
