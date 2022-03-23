package com.emlakburada.advert.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.emlakburada.advert.model.enums.AdvertStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
	private Integer advertId;
	private String title;
	private UserResponse creatorUser;
	private Integer creatorUserId;
	private Date creationTimeStamp;
	private BigDecimal price;
	private AdvertStatus advertStatus;
}
