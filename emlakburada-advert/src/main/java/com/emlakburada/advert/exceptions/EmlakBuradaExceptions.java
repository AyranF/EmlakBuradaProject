package com.emlakburada.advert.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@SuppressWarnings("serial")
@Getter
public class EmlakBuradaExceptions extends RuntimeException{
	private String message;
	private HttpStatus httpstatus;
	protected EmlakBuradaExceptions(String message, HttpStatus httpstatus) {
		this.message = message;
		this.httpstatus = httpstatus;
	}
	
}
