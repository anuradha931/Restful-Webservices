package com.example.rest.webservices.restfulwebservices.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex,WebRequest request){
		ExceptionResponses exceptionResponses=new ExceptionResponses(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponses,HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex,WebRequest request){
		ExceptionResponses exceptionResponses=new ExceptionResponses(new Date(),ex.getMessage(),request.getDescription(false));
		return new ResponseEntity(exceptionResponses,HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponses exceptionResponses=new ExceptionResponses(new Date(),"Not Valid Name",ex.getBindingResult().toString());
		return new ResponseEntity(exceptionResponses,HttpStatus.BAD_REQUEST);
	}
}
