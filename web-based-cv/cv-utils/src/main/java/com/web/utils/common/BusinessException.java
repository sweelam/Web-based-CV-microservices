package com.web.utils.common;

import lombok.Data;

@Data
public class BusinessException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	private String devErrorMessage;
	private String errorStatus;
	private int statusCode;

	public BusinessException(String message, String devErrorMessage, String httpStatus, int statusCode) {
		super();
		this.message = message;
		this.devErrorMessage = devErrorMessage;
		this.errorStatus = httpStatus;
		this.statusCode = statusCode;
	}

}
