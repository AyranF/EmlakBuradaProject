package com.emlakburada.user.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.user.dto.ProductRequest;

@Service
public class RabbitMqPurchaseListenerService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	ProductService productService;
	
	@RabbitListener(queues = "${rabbitmq.purchasequeue}")
	public void receivePurchaseMessage(ProductRequest productRequest) {
		productService.updateUsersProduct(productRequest);
	}
}
