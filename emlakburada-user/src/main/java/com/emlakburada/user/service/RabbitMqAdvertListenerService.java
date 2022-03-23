package com.emlakburada.user.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.user.dto.AdvertRequest;

@Service
public class RabbitMqAdvertListenerService{
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	ProductService productService;

	@RabbitListener(queues = "${rabbitmq.userqueue}")
	public void receiveAdvertMessage(AdvertRequest advertRequest) {
		advertService.saveAdvert(advertRequest);
	}

}