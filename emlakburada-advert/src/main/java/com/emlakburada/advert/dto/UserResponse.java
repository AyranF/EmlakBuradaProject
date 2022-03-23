package com.emlakburada.advert.dto;

import lombok.Data;

@Data
public class UserResponse {
	private String userName;
	private String userSurname;
	private String eMail;
	private Integer userId;
}
