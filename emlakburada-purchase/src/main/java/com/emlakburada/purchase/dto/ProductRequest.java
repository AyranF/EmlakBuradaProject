package com.emlakburada.purchase.dto;

import java.util.Calendar;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
	private Integer userId;
	private String cardNumber;
	private Calendar purchaseTimeStamp;
}
