package com.emlakburada.purchase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.emlakburada.purchase.dto.ProductRequest;
import com.emlakburada.purchase.service.PurchaseService;
import com.emlakburada.purchase.util.JwtUtil;

@RestController
public class PurchaseController {
	
	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/purchase")
	public ResponseEntity<?> saveUser(@RequestHeader (name="Authorization") String token, @RequestBody ProductRequest productRequest) {
		purchaseService.savePurchase(jwtUtil.getUserId(token), productRequest);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
