package com.emlakburada.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponse {
	private Boolean hasProduct;
	private Integer userId;
}
