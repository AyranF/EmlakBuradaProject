package com.emlakburada.advert.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.advert.config.RabbitMqReviewConfig;
import com.emlakburada.advert.config.RabbitMqUserConfig;
import com.emlakburada.advert.dto.ReviewRequest;
import com.emlakburada.advert.dto.UserRequest;

@Service
public class RabbitMqService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqUserConfig configUser;
	
	@Autowired
	private RabbitMqReviewConfig configReview;

	public void sendNewAdvertToUser(UserRequest userRequest) {
		rabbitTemplate.convertAndSend(configUser.getExchange(), configUser.getRoutingkey(), userRequest);
	}
	
	public void sendNewAdvertToReview(ReviewRequest reviewRequest) {
		rabbitTemplate.convertAndSend(configReview.getExchange(), configReview.getRoutingkey(), reviewRequest);
	}
}
