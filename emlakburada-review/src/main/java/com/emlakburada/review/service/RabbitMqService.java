package com.emlakburada.review.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.review.config.RabbitMqConfig;
import com.emlakburada.review.dto.AdvertRequest;

@Service
public class RabbitMqService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;
	
	public void sendReviewToAdvert(AdvertRequest advertRequest) {
		rabbitTemplate.convertAndSend(config.getAdvertExchange(), config.getAdvertRoutingkey(), advertRequest);
	}
}
