package com.emlakburada.review.config;

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
	
	@Value("${rabbitmq.reviewqueue}")
	private String reviewQueue;

	@Value("${rabbitmq.reviewexchange}")
	private String reviewExchange;

	@Value("${rabbitmq.reviewroutingkey}")
	private String reviewRoutingkey;
	
	@Value("${rabbitmq.advertqueue}")
	private String advertQueue;

	@Value("${rabbitmq.advertexchange}")
	private String advertExchange;

	@Value("${rabbitmq.advertroutingkey}")
	private String advertRoutingkey;

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
	public Queue ReviewQueue() {
		return new Queue(reviewQueue, false);
	}

	@Bean
	public DirectExchange ReviewExchange() {
		return new DirectExchange(reviewExchange);
	}

	@Bean
	public Binding purchaseBinding(Queue ReviewQueue, DirectExchange ReviewExchange) {
		return BindingBuilder.bind(ReviewQueue).to(ReviewExchange).with(reviewRoutingkey);
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
