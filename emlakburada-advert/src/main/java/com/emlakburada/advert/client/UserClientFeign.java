package com.emlakburada.advert.client;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emlakburada.advert.dto.UserResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class UserClientFeign {
	
	@Autowired
	private FeignClient feignClient;
	
	@org.springframework.cloud.openfeign.FeignClient(name="emlakburada-user")
	interface FeignClient{
		@GetMapping(value="/users/{userId}/advert")
		ResponseEntity<UserResponse> getUser(@PathVariable("userId") Integer userId);
		
	}

	@HystrixCommand(fallbackMethod = "getUserFallback")
	public ResponseEntity<UserResponse> getUser(Integer creatorUserId) {
		return feignClient.getUser(creatorUserId);
	}

	private ResponseEntity<UserResponse> getUserFallback(Integer creatorUserId) {
		UserResponse response = new UserResponse();
		response.setUserId(creatorUserId);
		return new ResponseEntity<UserResponse>(response, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
