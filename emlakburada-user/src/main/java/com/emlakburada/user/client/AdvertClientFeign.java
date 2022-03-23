package com.emlakburada.user.client;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emlakburada.user.dto.AdvertResponse;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class AdvertClientFeign{
	
	@Autowired
	private FeignClient feignClient;
	
	@HystrixCommand(fallbackMethod = "getAdvertsFallback")
	public Set<AdvertResponse> getAdverts(Integer userId) {
		Set<AdvertResponse> responseSet = new HashSet<>();
		ResponseEntity<Set<AdvertResponse>> response = feignClient.getAdverts(userId);
		if(response.getBody() != null)
			responseSet = response.getBody();
		return responseSet;
	}
	
	@HystrixCommand
	private Set<AdvertResponse> getAdvertsFallback(Integer userId) {
		return new HashSet<>();
	}
	
	@org.springframework.cloud.openfeign.FeignClient(name="emlakburada-advert")
	interface FeignClient {
		@GetMapping(value="adverts/{userId}/user")
		ResponseEntity<Set<AdvertResponse>> getAdverts(@PathVariable("userId") Integer userId);
	}
}

