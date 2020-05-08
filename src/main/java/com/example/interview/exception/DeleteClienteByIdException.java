package com.example.interview.exception;


public class DeleteClienteByIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DeleteClienteByIdException(String exception) {
		super(exception);
	}
}
