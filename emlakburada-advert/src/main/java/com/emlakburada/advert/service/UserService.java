package com.emlakburada.advert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.emlakburada.advert.client.UserClientFeign;
import com.emlakburada.advert.dto.UserRequest;
import com.emlakburada.advert.dto.UserResponse;
import com.emlakburada.advert.model.Advert;

@Service
public class UserService {
	
	@Autowired
	UserClientFeign userClientFeign;
	
	@Autowired
	RabbitMqService rabbitMqService;

	public void sendAdvet(Advert advert){
		rabbitMqService.sendNewAdvertToUser(convertAdvertToUserRequest(advert));
	}
	
	private UserRequest convertAdvertToUserRequest(Advert advert) {
		UserRequest userRequest = UserRequest.builder()
				.userId(advert.getCreatorUserId())
				.advertId(advert.getAdvertId())
				.build();
		return userRequest;
	}
	
	public UserResponse getUser(Integer userId) {
		ResponseEntity<UserResponse> response = userClientFeign.getUser(userId);
		if(response != null)
			return response.getBody();
		return null;
	}
}
