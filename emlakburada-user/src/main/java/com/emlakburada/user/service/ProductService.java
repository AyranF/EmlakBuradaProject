package com.emlakburada.user.service;

import org.springframework.stereotype.Service;

import com.emlakburada.user.dto.ProductRequest;
import com.emlakburada.user.dto.ProductResponse;
import com.emlakburada.user.model.User;

@Service
public class ProductService {
	
	public ProductResponse convertToProductResponse(User user) {
		ProductResponse productResponse;
		if(user.getProduct().getRemainingProduct() > 0) {
			user.getProduct().decrease();
			productResponse = ProductResponse.builder().hasProduct(true).userId(user.getUserId()).build();
		}
		else
			productResponse = ProductResponse.builder().hasProduct(false).userId(user.getUserId()).build();
		return productResponse;
	}

	public void updateUsersProduct(ProductRequest productRequest) {
		updateUsersProduct(productRequest);		
	}
}
