package com.revature.exceptions;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
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

		return new ResponseEntity<>(new ExceptionResponse("User not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end 
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<Object> handleUserAlreadyExistException(UserAlreadyExistException exception, WebRequest webRequest){

		return new ResponseEntity<>(new ExceptionResponse("User already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandAlreadyExistException.class)
	public ResponseEntity<Object> handleBrandAlreadyExistException(BrandAlreadyExistException exception, WebRequest webRequest){
		
		return new ResponseEntity<>(new ExceptionResponse("Brand already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(BrandNotFoundException.class)
	public ResponseEntity<Object> handleBrandNotFoundException(BrandNotFoundException exception, WebRequest webRequest) {
		
		return new ResponseEntity<>(new ExceptionResponse("Brand not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end
	
	@ExceptionHandler(FlavorAlreadyExistException.class)
	public ResponseEntity<Object> handleFlavorAlreadyExistException(FlavorAlreadyExistException exception, WebRequest webRequest){
		
		return new ResponseEntity<>(new ExceptionResponse("Flavor already exist", LocalDateTime.now()), HttpStatus.UNPROCESSABLE_ENTITY);
	}//end
	
	@ExceptionHandler(FlavorNotFoundException.class)
	public ResponseEntity<Object> handleFlavorNotFoundException(FlavorNotFoundException exception, WebRequest webRequest) {
		
		return new ResponseEntity<>(new ExceptionResponse("Flavor not found", LocalDateTime.now()), HttpStatus.NOT_FOUND);
	}//end
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest webRequest) {
		
		return new ResponseEntity<>(new ExceptionResponse("Data Integrity Violation", LocalDateTime.now()), HttpStatus.CONFLICT);
	}//end
	
}//end
