package com.emlakburada.advert.dto;

import java.math.BigDecimal;

import com.emlakburada.advert.model.enums.AdvertStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertRequest {
	private Integer advertId;
	private String title;
	private Integer creatorUserId;
	private BigDecimal price;
	@Builder.Default
	private AdvertStatus advertStatus = AdvertStatus.IN_REVIEW;
}
