package com.emlakburada.user.service;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emlakburada.user.dto.ProductRequest;
import com.emlakburada.user.dto.ProductResponse;
import com.emlakburada.user.dto.UserRequest;
import com.emlakburada.user.dto.UserResponse;
import com.emlakburada.user.exceptions.EmailAlreadyExistException;
import com.emlakburada.user.exceptions.UserNotFoundException;
import com.emlakburada.user.model.User;

@Service
public class UserService extends UserBaseService{
	
	public UserResponse saveUser(UserRequest userRequest){
		User user = convertUserRequestToUser(userRequest);
		if(!isUserExist(user.getEmail())) {
			user = userRepository.save(user);
			UserResponse response = convertUserToUserResponse(user);
			return response;
		}
		else
			throw new EmailAlreadyExistException("Email Already Exist!");
	}

	public ProductResponse getUsersProduct(int userId) {
		User user = findUserById(userId);
		Calendar expirationDate = getExpirationDate(user);
		ProductResponse productResponse = null;
		if(expirationDate.after(Calendar.getInstance())) {
			productResponse = productService.convertToProductResponse(user);
		}else {
			user.getProduct().setRemainingProduct(0);
			productResponse = productService.convertToProductResponse(user);
		}
		userRepository.save(user);
		return productResponse;
	}
	
	public UserResponse getUserRequestFromAdvert(int id) {
		User user = findUserById(id);
		UserResponse userResponse = convertUserToUserResponseWithoutAdvert(user);
		return userResponse;
	}

	public UserResponse getUserById(int id) {
		User user = findUserById(id);
		UserResponse userResponse = convertUserToUserResponse(user);
		return userResponse;
	}
	
	public UserResponse updateUser(UserRequest userRequest) {
		Optional<User> user = userRepository.findById(userRequest.getUserId());
		if(user.isEmpty())
			throw new UserNotFoundException("User Not Found");
		UserResponse userResponse = convertUserToUserResponse(Update(user.get(), userRequest));
		return userResponse;
	}

	public ProductResponse updateUsersProduct(ProductRequest productRequest) {
		User user = findUserById(productRequest.getUserId());
		Calendar expirationDate = getExpirationDate(user);
		if(!expirationDate.after(Calendar.getInstance())) {
			expirationDate = Calendar.getInstance();
			expirationDate.add(Calendar.MONTH, 1);
			user.getProduct().setRemainingProduct(10);
			user.getProduct().setExpirationDate(Calendar.getInstance());
			return updateUsersProduct(user);
		}
		expirationDate.add(Calendar.MONTH, 1);
		user.getProduct().setRemainingProduct(user.getProduct().getRemainingProduct()+10);
		user.getProduct().setExpirationDate(expirationDate);
		return updateUsersProduct(user);	
	}
}
