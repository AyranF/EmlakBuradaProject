package com.emlakburada.user.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class EmailAlreadyExistException extends EmlakBuradaExceptions{
	public EmailAlreadyExistException(String message) {
		super(message, HttpStatus.BAD_REQUEST);
	}
		
}

