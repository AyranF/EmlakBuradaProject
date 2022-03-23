package com.emlakburada.user.service;

import java.util.Calendar;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.emlakburada.user.client.AdvertClientFeign;
import com.emlakburada.user.dto.AdvertResponse;
import com.emlakburada.user.dto.ProductResponse;
import com.emlakburada.user.dto.UserRequest;
import com.emlakburada.user.dto.UserResponse;
import com.emlakburada.user.exceptions.UserNotFoundException;
import com.emlakburada.user.model.Product;
import com.emlakburada.user.model.User;
import com.emlakburada.user.repository.UserRepository;

public class UserBaseService {
	
	@Autowired
	protected UserRepository userRepository;
	
	@Autowired
	protected AdvertService advertService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired
	AdvertClientFeign advertClientFeign;
	
	protected User Update(User user, UserRequest userRequest) {
		if(userRequest.getEMail() != null)
			user.setEmail(userRequest.getEMail());
		if(userRequest.getPassword() != null)
			user.setPassword(userRequest.getPassword());
		if(userRequest.getUserName() != null)
			user.setUserName(userRequest.getUserName());
		if(userRequest.getUserSurname() != null)
			user.setUserSurname(userRequest.getUserSurname());
		user = userRepository.save(user);
		return user;
	}
	
	protected ProductResponse updateUsersProduct(User user) {
		user = userRepository.save(user);
		return productService.convertToProductResponse(user);
	}
	
	protected User findUserById(int id) {
		Optional<User> foundUser = userRepository.findById(id);
		if(foundUser.isEmpty())
			throw new UserNotFoundException("User Not Found!");
		return foundUser.get();
	}
	
	protected User convertUserRequestToUser(UserRequest userRequest) {
		User user = User.builder()
		.userId(userRequest.getUserId())
		.userName(userRequest.getUserName())
		.userSurname(userRequest.getUserSurname())
		.email(userRequest.getEMail())
		.password(userRequest.getPassword())
		.product(new Product())
		.build();
		return user;
	}
	
	protected UserResponse convertUserToUserResponseWithoutAdvert(User user) {
		return UserResponse.builder()
				.userId(user.getUserId())
				.userName(user.getUserName())
				.userSurname(user.getUserSurname())
				.eMail(user.getEmail())
				.remainingProduct(user.getProduct().getRemainingProduct())
				.build();
	}
	
	protected UserResponse convertUserToUserResponse(User user) {
		Set<AdvertResponse> adverts = advertService.getAdverts(user.getUserId());
		if(adverts != null)
			return UserResponse.builder()
					.userId(user.getUserId())
					.userName(user.getUserName())
					.userSurname(user.getUserSurname())
					.eMail(user.getEmail())
					.adverts(adverts)
					.remainingProduct(user.getProduct().getRemainingProduct())
					.build();
		return convertUserToUserResponseWithoutAdvert(user);
	}
	
	protected Calendar getExpirationDate(User user) {
		return user.getProduct().getExpirationDate();
	}
	
	protected boolean isUserExist(String email) {
		Optional<User> user = userRepository.findByEmail(email);
		return user.isPresent(); 
	}
}
