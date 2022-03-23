package com.emlakburada.user.dto;

import java.util.Set;

import com.emlakburada.user.model.Advert;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
	
	private String userName;
	private String userSurname;
	private String eMail;
	private String password;
	private Integer userId;
	private Set<Advert> adverts;

}
