package com.example.interview.exception;


public class UpdateClienteNomeByIdException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UpdateClienteNomeByIdException(String exception) {
		super(exception);
	}
}
