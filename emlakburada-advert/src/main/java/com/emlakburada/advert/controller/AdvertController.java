package com.emlakburada.advert.controller;

import java.util.Set;

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

import com.emlakburada.advert.dto.AdvertRequest;
import com.emlakburada.advert.dto.AdvertResponse;
import com.emlakburada.advert.service.AdvertService;
import com.emlakburada.advert.util.JwtUtil;

@RestController
public class AdvertController {
	
	@Autowired
	AdvertService advertService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/adverts")
	public ResponseEntity<AdvertResponse> saveAdvert(@RequestHeader (name="Authorization") String token, @RequestBody AdvertRequest advertRequest){
		advertRequest.setCreatorUserId(jwtUtil.getUserId(token));
		return new ResponseEntity<>(advertService.saveAdvert(advertRequest), HttpStatus.OK);
	}
	
	@GetMapping("/adverts/{userId}")
	public ResponseEntity<Set<AdvertResponse>> getAdvertsByUserId(@PathVariable(required = false) int userId){
		Set<AdvertResponse> responseSet = advertService.getAdverts(userId);
		return new ResponseEntity<>(responseSet, HttpStatus.OK);
	}
	
	@GetMapping("/adverts/{userId}/user")
	public ResponseEntity<Set<AdvertResponse>> getAdvertsByUserIdFromUser(@PathVariable(required = false) int userId){
		Set<AdvertResponse> responseSet = advertService.getAdvertsByUserIdFromUser(userId);
		return new ResponseEntity<>(responseSet, HttpStatus.OK);
	}
	
	@GetMapping("/adverts/active")
	public ResponseEntity<Set<AdvertResponse>> getActiveAdvertsByUserId(@RequestHeader (name="Authorization") String token){
		Set<AdvertResponse> responseSet = advertService.getActiveAdverts(jwtUtil.getUserId(token));
		return new ResponseEntity<>(responseSet, HttpStatus.OK);
	}
	
	@GetMapping("/adverts/passive")
	public ResponseEntity<Set<AdvertResponse>> getPassiveAdvertsByUserId(@RequestHeader (name="Authorization") String token){
		Set<AdvertResponse> responseSet = advertService.getPassiveAdverts(jwtUtil.getUserId(token));
		return new ResponseEntity<>(responseSet, HttpStatus.OK);
	}
	
	@GetMapping("/adverts")
	public ResponseEntity<Set<AdvertResponse>> getAllAdvert(){
		Set<AdvertResponse> responseSet = advertService.getAdverts();
		return new ResponseEntity<>(responseSet, HttpStatus.OK);
	}
	
	@PutMapping("/adverts")
	public ResponseEntity<AdvertResponse> updateAdvert(@RequestHeader (name="Authorization") String token, @RequestBody AdvertRequest advertRequest){
		advertRequest.setCreatorUserId(jwtUtil.getUserId(token));
		return new ResponseEntity<>(advertService.updateAdvert(advertRequest), HttpStatus.OK);
	}
}
