package com.emlakburada.user.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class UserNotFoundException extends EmlakBuradaExceptions{

	public UserNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
	
}
