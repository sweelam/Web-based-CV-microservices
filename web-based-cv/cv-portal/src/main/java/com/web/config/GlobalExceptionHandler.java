package com.web.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.web.utils.common.BusinessException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleException(BusinessException ex, WebRequest errorMessage) {

		if(ex.getStatusCode() == 500)
			return new ResponseEntity<>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
		else
			return new ResponseEntity<>(ex, HttpStatus.valueOf(ex.getStatusCode()));

	}
}
