package com.emlakburada.user.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class RabbitMqConfig {
	
	@Value("${rabbitmq.userqueue}")
	private String advertQueue;

	@Value("${rabbitmq.userexchange}")
	private String advertExchange;

	@Value("${rabbitmq.userroutingkey}")
	private String advertRoutingkey;
	

	@Value("${rabbitmq.purchasequeue}")
	private String purchaseQueue;

	@Value("${rabbitmq.purchaseexchange}")
	private String purchaseExchange;

	@Value("${rabbitmq.purchaseroutingkey}")
	private String purchaseRoutingkey;

	@Bean
	public Queue AdvertQueue() {
		return new Queue(advertQueue, false);
	}

	@Bean
	public DirectExchange AdvertExchange() {
		return new DirectExchange(advertExchange);
	}

	@Bean
	public Binding AdvertBinding(Queue AdvertQueue, DirectExchange AdvertExchange) {
		return BindingBuilder.bind(AdvertQueue).to(AdvertExchange).with(advertRoutingkey);
	}
	
	@Bean
	public Queue PurchaseQueue() {
		return new Queue(purchaseQueue, false);
	}

	@Bean
	public DirectExchange PurchaseExchange() {
		return new DirectExchange(purchaseExchange);
	}

	@Bean
	public Binding purchaseBinding(Queue PurchaseQueue, DirectExchange PurchaseExchange) {
		return BindingBuilder.bind(PurchaseQueue).to(PurchaseExchange).with(purchaseRoutingkey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}
}
