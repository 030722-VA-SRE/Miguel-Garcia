package com.revature.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/*
 * ResponseEntityExceptionHandler is a convenient base class that wishes to provide
 * centralized exception handling across all @RequestMapping methods through @ExceptionHandler methods.
 */
//@Controller advice allows you to handle exceptions across the whole application
@ControllerAdvice
public class CustomizedExceptionHandling extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("User not found");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}//end 
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception, WebRequest webRequest){
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("User already exist");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandAlreadyExistException.class)
	public ResponseEntity<Object> handleBrandAlreadyExistException(BrandAlreadyExistException exception, WebRequest webRequest){
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("Brand already exist");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandNotFoundException.class)
	public ResponseEntity<Object> handleBrandNotFoundException(BrandNotFoundException exception, WebRequest webRequest) {
		ExceptionResponse response = new ExceptionResponse();
		response.setMessage("Brand not found");
		response.setDateTime(LocalDateTime.now());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}//end 
	
}//end
