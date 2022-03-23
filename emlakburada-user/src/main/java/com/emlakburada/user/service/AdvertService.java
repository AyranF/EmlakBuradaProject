package com.emlakburada.user.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.emlakburada.user.dto.AdvertRequest;
import com.emlakburada.user.dto.AdvertResponse;
import com.emlakburada.user.exceptions.UserNotFoundException;
import com.emlakburada.user.model.Advert;


@Service
public class AdvertService extends AdvertBaseService{
	
	public Advert saveAdvert(AdvertRequest advertRequest){
		Advert advert = advertRequestToAdvert(advertRequest);
		advert = advertRepository.save(advert);
		return advert;
	}

	public AdvertResponse findAdvertById(int id) {
		Optional<Advert> foundAdvert = advertRepository.findById(id);
		AdvertResponse response = convertAdvertToResponse(foundAdvert.get());
		if(response != null)
			return response;
		throw new UserNotFoundException("User Not Found");
	}

	public List<AdvertRequest> findAdvertsById(Integer userId) {
		List<AdvertRequest> requests = new ArrayList<>();
		for(Advert advert : advertRepository.findAdvertByUserId(userId)) {
			requests.add(convertAdvertToRequest(advert));
		}
		return requests;
	}

	public Set<AdvertResponse> getAdverts(Integer userId) {
		Set<AdvertResponse> responses = advertClientFeign.getAdverts(userId);
		if(responses.size() != 0) {
			return responses;
		}
		return null;
	}

	public Set<AdvertResponse> convertAdvertSetToAdvertResponseSet(Set<Advert> adverts) {
		Set<AdvertResponse> advertResponseSet = new HashSet<>(); 
		if(adverts == null)
			return advertResponseSet;
		for(Advert a : adverts)
			advertResponseSet.add(AdvertResponse.builder().advertId(a.getAdvertId()).build());
		return advertResponseSet;
	}

}
