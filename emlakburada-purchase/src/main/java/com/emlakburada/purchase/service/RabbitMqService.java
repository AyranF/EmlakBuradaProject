package com.emlakburada.purchase.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.purchase.config.RabbitMqConfig;
import com.emlakburada.purchase.dto.ProductRequest;

@Service
public class RabbitMqService {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Autowired
	private RabbitMqConfig config;


	public void sendProductToUser(ProductRequest productRequest) {
		rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), productRequest);
	}
}
