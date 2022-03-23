package com.emlakburada.advert.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emlakburada.advert.client.UserClientFeign;
import com.emlakburada.advert.dto.AdvertRequest;
import com.emlakburada.advert.dto.AdvertResponse;
import com.emlakburada.advert.dto.UserResponse;
import com.emlakburada.advert.model.Advert;
import com.emlakburada.advert.model.enums.AdvertStatus;
import com.emlakburada.advert.repository.AdvertRepository;

@Service
public class AdvertBaseService {

	@Autowired
	AdvertRepository advertRepository;
	
	@Autowired
	RabbitMqService rabbitMqService;
 
	@Autowired
	UserClientFeign userFeignClient;
	
	@Autowired
	UserService userService;

	@Autowired
	ReviewService reviewService;
	
	protected Advert convertAdvertRequestToAdvert(AdvertRequest advertRequest) {
		Advert advert = Advert.builder()
				.advertId(advertRequest.getAdvertId())
				.title(advertRequest.getTitle())
				.advertStatus(advertRequest.getAdvertStatus())
				.creationTimeStamp(new Date())
				.price(advertRequest.getPrice())
				.creatorUserId(advertRequest.getCreatorUserId())
				.build();
		if(advertRequest.getAdvertStatus() == null)
			advert.setAdvertStatus(AdvertStatus.IN_REVIEW);
		return advert;
	}
	
	protected AdvertResponse convertAdvertToAdvertResponse(Advert advert) {
		UserResponse userResponse = userService.getUser(advert.getCreatorUserId());
		AdvertResponse response = AdvertResponse.builder()
						.advertId(advert.getAdvertId())
						.title(advert.getTitle())
						.creatorUser(userResponse)
						.creationTimeStamp(advert.getCreationTimeStamp())
						.price(advert.getPrice())
						.advertStatus(advert.getAdvertStatus())
						.build();
		return response;
	}
	
	protected AdvertResponse convertAdvertToAdvertResponseWithoutUser(Advert advert) {
		AdvertResponse response = AdvertResponse.builder()
						.advertId(advert.getAdvertId())
						.title(advert.getTitle())
						.creationTimeStamp(advert.getCreationTimeStamp())
						.price(advert.getPrice())
						.advertStatus(advert.getAdvertStatus())
						.build();
		return response;
	}
	
	protected Set<AdvertResponse> convertAdvertsToResponse(List<Advert> adverts) {
		Set<AdvertResponse> advertResponses = new HashSet<>();
		for(Advert advert : adverts) {
			AdvertResponse response = AdvertResponse.builder()
					.advertId(advert.getAdvertId())
					.advertStatus(advert.getAdvertStatus())
					.creationTimeStamp(advert.getCreationTimeStamp())
					.creatorUserId(advert.getCreatorUserId())
					.price(advert.getPrice())
					.title(advert.getTitle())
					.build();
			ResponseEntity<UserResponse> user = userFeignClient.getUser(advert.getCreatorUserId());
			if(user.getStatusCode() == HttpStatus.OK)
				response.setCreatorUser((UserResponse) user.getBody());
			advertResponses.add(response);
		}
		return advertResponses;
	}
}
