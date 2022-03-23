package com.emlakburada.purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emlakburada.purchase.client.UserClientFeign;
import com.emlakburada.purchase.dto.ProductRequest;
import com.emlakburada.purchase.model.Product;
import com.emlakburada.purchase.repository.PurchaseRepository;

@Service
public class PurchaseService {
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	UserClientFeign userClientFeign;

	public void savePurchase(Integer userId, ProductRequest productRequest) {
		if(!creditCardProcess(productRequest.getCardNumber()))
			return;
		Product product = convertProductRequestToProduct(userId, productRequest);
		product = purchaseRepository.save(product);
		userClientFeign.putProduct(convertProductToProductRequest(product));
	}

	private Product convertProductRequestToProduct(Integer userId, ProductRequest productRequest) {
		return Product.builder()
				.userId(userId)
				.build();
	}
	
	private ProductRequest convertProductToProductRequest(Product product) {
		return ProductRequest.builder()
				.userId(product.getUserId())
				.purchaseTimeStamp(product.getPurchaseTimeStamp())
				.build();
	}
	
	private boolean creditCardProcess(String cardNumber) { return true; }
}
