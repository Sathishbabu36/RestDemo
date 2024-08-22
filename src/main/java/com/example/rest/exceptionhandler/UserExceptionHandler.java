package com.example.rest.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.exceptions.UserNotFoundByIdException;
import com.example.rest.util.AppResponseBuilder;
import com.example.rest.util.ErrorStructure;

@RestControllerAdvice
public class UserExceptionHandler {

	private AppResponseBuilder responseBuilder;

	public UserExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}


	@ExceptionHandler(UserNotFoundByIdException.class)
	public ResponseEntity<ErrorStructure<String>> handleUserNotFoundById(UserNotFoundByIdException ex){
		return responseBuilder.create(HttpStatus.NOT_FOUND,ex.getMessage(),"user not found by the given Id");
	}
}
