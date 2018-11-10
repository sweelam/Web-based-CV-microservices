package com.me.website.config;

import com.me.website.common.ApiExceptionVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(ApiExceptionVO.class)
//    public final ResponseEntity<ApiExceptionVO> handleException(Exception ex, ApiExceptionVO body, HttpStatus statusCode) {
//        return this.internalApiErrorHandler(body, new HttpHeaders(), statusCode);
//
//    }
//
//    /**
//     *
//     * @param body
//     * @param httpHeaders
//     * @param status
//     * @return
//     */
//    private final ResponseEntity<ApiExceptionVO> internalApiErrorHandler(ApiExceptionVO body, HttpHeaders httpHeaders, HttpStatus status) {
//        if(status == HttpStatus.INTERNAL_SERVER_ERROR)
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        else
//            return new ResponseEntity<>(body, httpHeaders , status);
//    }

}
