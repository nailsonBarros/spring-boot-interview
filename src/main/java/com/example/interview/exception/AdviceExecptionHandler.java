package com.example.interview.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class AdviceExecptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
														  HttpStatus status, WebRequest request) {
		CustomizedResponse exceptionResponse = new CustomizedResponse(
				ex.getBindingResult().getAllErrors().get(0).getDefaultMessage(), HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<CustomizedResponse> handleBusinessException(CustomException ex) {
		CustomizedResponse customizedResponseException = new CustomizedResponse(ex.getMessage(),
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(customizedResponseException, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DeleteClienteByIdException.class)
	public final ResponseEntity<CustomizedResponse> handleDeleteClienteByIdException(DeleteClienteByIdException ex) {
		CustomizedResponse customizedResponseException = new CustomizedResponse(ex.getMessage(),
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(customizedResponseException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(FindClienteByIdException.class)
	public final ResponseEntity<CustomizedResponse> handleFindClienteByIdException(FindClienteByIdException ex) {
		CustomizedResponse customizedResponseException = new CustomizedResponse(ex.getMessage(),
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(customizedResponseException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InsertClienteException.class)
	public final ResponseEntity<CustomizedResponse> handleInsertClienteException(InsertClienteException ex) {
		CustomizedResponse customizedResponseException = new CustomizedResponse(ex.getMessage(),
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(customizedResponseException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UpdateClienteNomeByIdException.class)
	public final ResponseEntity<CustomizedResponse> handleUpdateClienteNomeByIdException(UpdateClienteNomeByIdException ex) {
		CustomizedResponse customizedResponseException = new CustomizedResponse(ex.getMessage(),
				HttpStatus.BAD_REQUEST.toString());
		return new ResponseEntity<>(customizedResponseException, HttpStatus.BAD_REQUEST);
	}
	
}
