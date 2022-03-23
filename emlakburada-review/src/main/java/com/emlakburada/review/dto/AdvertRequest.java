package com.emlakburada.review.dto;

import java.math.BigDecimal;

import com.emlakburada.review.dto.enums.AdvertStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertRequest {
	
	public AdvertRequest(Integer creatorUserId ,Integer advertId, AdvertStatus advertStatus) {
		this.advertId = advertId;
		this.advertStatus = advertStatus;
		this.creatorUserId = creatorUserId;
	}
	private Integer advertId;
	private String title;
	private Integer creatorUserId;
	private BigDecimal price;
	private AdvertStatus advertStatus;

}