package com.emlakburada.advert.exceptions;

import org.springframework.http.HttpStatus;

@SuppressWarnings("serial")
public class AdvertInReviewException extends EmlakBuradaExceptions {

	public AdvertInReviewException(String message) {
		super(message, HttpStatus.PROCESSING);
	}

}
