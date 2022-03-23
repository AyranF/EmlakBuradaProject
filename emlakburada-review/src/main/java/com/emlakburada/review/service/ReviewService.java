package com.emlakburada.review.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.review.client.FeignClientToUser;
import com.emlakburada.review.dto.AdvertRequest;
import com.emlakburada.review.dto.ProductResponse;
import com.emlakburada.review.dto.enums.AdvertStatus;

@Service
public class ReviewService {
	
	@Autowired
	FeignClientToUser feignClientToUser;
	
	@Autowired
	RabbitMqService rabbitMqService;
	
	public void review(AdvertRequest reviewRequest) {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ProductResponse productResponse = feignClientToUser.hasProduct(reviewRequest.getCreatorUserId());
		if(productResponse.getHasProduct())
			rabbitMqService.sendReviewToAdvert(new AdvertRequest(reviewRequest.getCreatorUserId(),reviewRequest.getAdvertId(), AdvertStatus.ACTIVE));
		else 
			rabbitMqService.sendReviewToAdvert(new AdvertRequest(reviewRequest.getCreatorUserId(),reviewRequest.getAdvertId(), AdvertStatus.PASSIVE));
	}

}
