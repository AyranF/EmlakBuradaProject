package com.emlakburada.review.dto;

import com.emlakburada.review.dto.enums.AdvertStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AdvertResponse {
	private Integer advertId;
	private AdvertStatus advertStatus;
}
