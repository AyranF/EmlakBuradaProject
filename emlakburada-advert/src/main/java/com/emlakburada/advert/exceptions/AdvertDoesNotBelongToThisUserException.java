package com.emlakburada.advert.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class AdvertDoesNotBelongToThisUserException extends EmlakBuradaExceptions {
	
	public AdvertDoesNotBelongToThisUserException(String message) {
		super(message, HttpStatus.UNAUTHORIZED);
	}
}
