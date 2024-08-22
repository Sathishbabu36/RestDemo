package com.example.rest.exceptions;

@SuppressWarnings("serial")
public class UserNotFoundByIdException extends RuntimeException{
	private String message;

	public UserNotFoundByIdException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
