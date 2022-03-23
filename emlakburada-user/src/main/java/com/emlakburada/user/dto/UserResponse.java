package com.emlakburada.user.dto;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
	private String userName;
	private String userSurname;
	private String eMail;
	private Integer userId;
	private Set<AdvertResponse> adverts;
	private Integer remainingProduct;

}
