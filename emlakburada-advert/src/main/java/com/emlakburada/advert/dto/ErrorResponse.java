package com.emlakburada.advert.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
	private HttpStatus httpstatus;
	private String message;
	private Date date;
}
