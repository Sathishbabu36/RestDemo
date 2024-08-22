package com.example.rest.exceptionhandler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.rest.util.AppResponseBuilder;
import com.example.rest.util.CustomFieldError;

@RestControllerAdvice
public class FieldErrorExceptionHandler extends ResponseEntityExceptionHandler{

	AppResponseBuilder responseBuilder;
	
	public FieldErrorExceptionHandler(AppResponseBuilder responseBuilder) {
		super();
		this.responseBuilder = responseBuilder;
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		// TODO Auto-generated method stub
		List<ObjectError> objectErrors  = ex.getAllErrors();
		List<CustomFieldError> errors = new ArrayList<>();
		for(ObjectError error : objectErrors) {
			FieldError fieldError = (FieldError) error;
            
			errors.add(CustomFieldError.create(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return responseBuilder.fieldError(HttpStatus.BAD_REQUEST, "BadRequest,invalidinputs", errors);
	}
}
