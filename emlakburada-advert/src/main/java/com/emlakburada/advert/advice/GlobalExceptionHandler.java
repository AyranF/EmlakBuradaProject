package com.emlakburada.advert.advice;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emlakburada.advert.dto.ErrorResponse;
import com.emlakburada.advert.exceptions.AdvertDoesNotBelongToThisUserException;
import com.emlakburada.advert.exceptions.AdvertInReviewException;
import com.emlakburada.advert.exceptions.AdvertNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AdvertNotFoundException.class)
	public ResponseEntity<ErrorResponse> handle(AdvertNotFoundException exception) {
		log.error(exception.getMessage());
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.httpstatus(exception.getHttpstatus())
				.date(new Date())
				.build();
		return new ResponseEntity<>(response, exception.getHttpstatus());
	}
	
	@ExceptionHandler(AdvertInReviewException.class)
	public ResponseEntity<ErrorResponse> handle(AdvertInReviewException exception) {
		log.error(exception.getMessage());
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.httpstatus(exception.getHttpstatus())
				.date(new Date())
				.build();
		return new ResponseEntity<>(response, exception.getHttpstatus());
	}
	
	@ExceptionHandler(AdvertDoesNotBelongToThisUserException.class)
	public ResponseEntity<ErrorResponse> handle(AdvertDoesNotBelongToThisUserException exception) {
		log.error(exception.getMessage());
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.httpstatus(exception.getHttpstatus())
				.date(new Date())
				.build();
		return new ResponseEntity<>(response, exception.getHttpstatus());
	}

}
