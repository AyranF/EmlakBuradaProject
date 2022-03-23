package com.emlakburada.advert.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class AdvertNotFoundException extends EmlakBuradaExceptions{

	public AdvertNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND);
	}
	
}
