package com.example.interview.exception;


public class FindClienteByIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public FindClienteByIdException(String exception) {
		super(exception);
	}
}
