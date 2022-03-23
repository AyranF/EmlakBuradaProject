package com.emlakburada.advert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.advert.dto.ReviewRequest;
import com.emlakburada.advert.model.Advert;

@Service
public class ReviewService {
	
	@Autowired
	private RabbitMqService rabbitMqService;
	
	public void sendAdvet(Advert advert){
		rabbitMqService.sendNewAdvertToReview(convertAdvertToReviewRequest(advert));
	}
	
	private ReviewRequest convertAdvertToReviewRequest(Advert advert) {
		ReviewRequest response = ReviewRequest.builder()
						.advertId(advert.getAdvertId())
						.title(advert.getTitle())
						.creatorUserId(advert.getCreatorUserId())
						.creationTimeStamp(advert.getCreationTimeStamp())
						.price(advert.getPrice())
						.advertStatus(advert.getAdvertStatus())
						.build();
		return response;
		}
}
