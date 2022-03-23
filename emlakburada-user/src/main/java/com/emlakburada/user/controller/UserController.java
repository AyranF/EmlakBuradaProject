package com.emlakburada.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.emlakburada.user.dto.ProductRequest;
import com.emlakburada.user.dto.ProductResponse;
import com.emlakburada.user.dto.UserRequest;
import com.emlakburada.user.dto.UserResponse;
import com.emlakburada.user.service.UserService;
import com.emlakburada.user.util.JwtUtil;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/users")
	public ResponseEntity<?> saveUser(@RequestBody UserRequest userRequest) {
		return new ResponseEntity<>(userService.saveUser(userRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/users/{userId}")
	public ResponseEntity<?> getUser(@PathVariable(required = false) int userId){
		UserResponse userResponse = userService.getUserById(userId);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	
	@GetMapping("/users/{userId}/advert")
	public ResponseEntity<?> getUserRequestFromAdvert(@PathVariable(required = false) int userId){
		UserResponse userResponse = userService.getUserRequestFromAdvert(userId);
		return new ResponseEntity<>(userResponse,HttpStatus.OK);
	}
	
	@PutMapping("/users/{userId}/products")
	public ResponseEntity<?> getUsersProduct(@RequestBody ProductRequest productRequest){
		ProductResponse productResponse = userService.updateUsersProduct(productRequest);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}

	@GetMapping("/users/{userId}/products")
	public ResponseEntity<?> getUsersProduct(@PathVariable(required = false) int userId){
		ProductResponse productResponse = userService.getUsersProduct(userId);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> updateUser(@RequestHeader (name="Authorization") String token, @RequestBody UserRequest userRequest){
		if(userRequest.getUserId() == jwtUtil.getUserId(token))
			return new ResponseEntity<>(userService.updateUser(userRequest), HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}	
}
