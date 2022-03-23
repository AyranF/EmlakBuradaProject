package com.emlakburada.advert.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.advert.config.RabbitMqAdvertConfig;
import com.emlakburada.advert.dto.AdvertRequest;

@Service
public class RabbitMqListenerService{
	
	@Autowired
	RabbitMqAdvertConfig rabbitMqConfig;

	@Autowired
	AdvertService advertService;

	@RabbitListener(queues = "${rabbitmq.advertqueue}")
	public void receiveMessage(AdvertRequest advertRequest) {
		advertService.changeReviewStatus(advertRequest);
	}
}