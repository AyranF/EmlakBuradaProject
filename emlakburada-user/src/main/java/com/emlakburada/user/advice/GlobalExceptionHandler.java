package com.emlakburada.user.advice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.emlakburada.user.dto.ErrorResponse;
import com.emlakburada.user.exceptions.EmailAlreadyExistException;
import com.emlakburada.user.exceptions.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(SQLIntegrityConstraintViolationException exception) {
		log.error(exception.getMessage());
		if(exception.getMessage().startsWith("Duplicate entry")) {
			ErrorResponse response = ErrorResponse.builder()
					.message("Email already exist")
					.httpstatus(HttpStatus.FORBIDDEN)
					.date(new Date())
					.build();
			return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
		}
		return null;
	}
	
	@ExceptionHandler(EmailAlreadyExistException.class)
	public ResponseEntity<ErrorResponse> handle(EmailAlreadyExistException exception) {
		log.error(exception.getMessage());
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.httpstatus(exception.getHttpstatus())
				.date(new Date())
				.build();
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handle(UserNotFoundException exception) {
		log.error(exception.getMessage());
		ErrorResponse response = ErrorResponse.builder()
				.message(exception.getMessage())
				.httpstatus(exception.getHttpstatus())
				.date(new Date())
				.build();
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
