package com.emlakburada.review.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.emlakburada.review.dto.ProductResponse;

@org.springframework.cloud.openfeign.FeignClient(name = "emlakburada-user")
public interface FeignClientToUser {
	
	@GetMapping(value = "users/{userId}/products")
	ProductResponse hasProduct(@PathVariable int userId);
}
