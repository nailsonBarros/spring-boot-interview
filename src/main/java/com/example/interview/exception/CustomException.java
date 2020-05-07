package com.example.interview.exception;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
	
	public CustomException(String exception) {
		super(exception);
	}
}
