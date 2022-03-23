package com.emlakburada.purchase.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.emlakburada.purchase.dto.ProductRequest;
import com.emlakburada.purchase.service.RabbitMqService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserClientFeign{
	
	@Autowired
	private FeignClient feignClient;
	
	@Autowired
	private RabbitMqService rabbitMqService;
	
	@HystrixCommand(fallbackMethod = "defaultPutProductt")
	public void putProduct(ProductRequest productRequest) {
		feignClient.putProduct(productRequest.getUserId(), productRequest);
	}

	@SuppressWarnings("unused")
	private void defaultPutProductt(ProductRequest productRequest){
		log.info("Fallbackmethod works");
		rabbitMqService.sendProductToUser(productRequest);
	}
	
	@org.springframework.cloud.openfeign.FeignClient(name="emlakburada-user")
	interface FeignClient {
		@PutMapping(value="users/{userId}/products")
		void putProduct(@PathVariable("userId") Integer userId, ProductRequest productRequest);
	}
}

