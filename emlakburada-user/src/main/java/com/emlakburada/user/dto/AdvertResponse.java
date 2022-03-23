package com.emlakburada.user.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.emlakburada.user.model.enums.AdvertStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdvertResponse {
	private Integer advertId;
	private String title;
	private Integer creatorUserId;
	private Date creationTimeStamp;
	private BigDecimal price;
	private AdvertStatus advertStatus;
}
