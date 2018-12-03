package com.web.controller;


import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.web.ApplicationUtil.ApiErrorHandling;

@ControllerAdvice
public class MyProfileControllerAdvise extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiErrorHandling.class)
    ResponseEntity<?> handleContollerException(HttpServletRequest http) {
        HttpStatus status = getStatus(http);

        ApiErrorHandling errorHandling = new ApiErrorHandling();
        errorHandling.setErrorCode(status.value());
        errorHandling.setError(Arrays.asList(status.getReasonPhrase()));
        errorHandling.setMessage("No data found");

        return new ResponseEntity<ApiErrorHandling>(errorHandling, HttpStatus.NOT_FOUND);
    }

    private HttpStatus getStatus(HttpServletRequest httpRequest) {
        Integer statusCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null)
            return HttpStatus.INTERNAL_SERVER_ERROR;

        return HttpStatus.valueOf(statusCode);

    }

}
