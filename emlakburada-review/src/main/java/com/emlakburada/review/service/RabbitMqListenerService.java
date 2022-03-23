package com.emlakburada.review.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.review.config.RabbitMqConfig;
import com.emlakburada.review.dto.AdvertRequest;

@Service
public class RabbitMqListenerService{
	
	@Autowired
	RabbitMqConfig rabbitMqConfig;
	
	@Autowired
	ReviewService reviewService;

	@RabbitListener(queues = "${rabbitmq.reviewqueue}")
	public void receiveMessage(AdvertRequest advertRequest) {
		reviewService.review(advertRequest);
	}
}