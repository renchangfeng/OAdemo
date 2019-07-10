package com.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomException extends RuntimeException {

	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}
	
}
