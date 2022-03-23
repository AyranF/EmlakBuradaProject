package com.emlakburada.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.user.client.AdvertClientFeign;
import com.emlakburada.user.dto.AdvertRequest;
import com.emlakburada.user.dto.AdvertResponse;
import com.emlakburada.user.model.Advert;
import com.emlakburada.user.repository.AdvertRepository;

@Service
public class AdvertBaseService {
	
	@Autowired
	AdvertRepository advertRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AdvertClientFeign advertClientFeign;
	
	protected Advert advertRequestToAdvert(AdvertRequest advertRequest) {
		Advert advert = Advert.builder()
				.advertId(advertRequest.getAdvertId())
				.creatorUser(userService.findUserById(advertRequest.getUserId()))
				.build();
		return advert;
	}

	protected AdvertRequest convertAdvertToRequest(Advert advert) {
		AdvertRequest request = AdvertRequest.builder().advertId(advert.getAdvertId()).build();
		return request;
	}
	
	protected AdvertResponse convertAdvertToResponse(Advert advert) {
		AdvertResponse response = AdvertResponse.builder()
				.advertId(advert.getAdvertId())
				.build();
		return response;
	}
}
