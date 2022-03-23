package com.emlakburada.advert.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.emlakburada.advert.dto.AdvertRequest;
import com.emlakburada.advert.dto.AdvertResponse;
import com.emlakburada.advert.exceptions.AdvertDoesNotBelongToThisUserException;
import com.emlakburada.advert.exceptions.AdvertInReviewException;
import com.emlakburada.advert.exceptions.AdvertNotFoundException;
import com.emlakburada.advert.model.Advert;
import com.emlakburada.advert.model.enums.AdvertStatus;

@Service
public class AdvertService extends AdvertBaseService{
	
	public AdvertResponse saveAdvert(AdvertRequest advertRequest) {
		Advert advert = convertAdvertRequestToAdvert(advertRequest);
		advert = advertRepository.save(advert);
		AdvertResponse advertResponse = convertAdvertToAdvertResponse(advert);
		userService.sendAdvet(advert);
		reviewService.sendAdvet(advert);
		return advertResponse;
	}

	public Advert findById(Integer advertId) {
		Optional<Advert> advert = advertRepository.findById(advertId);
		if(advert.isPresent())
			return advert.get();
		throw new AdvertNotFoundException("Advert Not Found");
	}
	
	public Set<AdvertResponse> getAdverts(Integer userId) {
		Set<AdvertResponse> responseSet = new HashSet<>();
		Iterable<Advert> adverts = advertRepository.findAdvertByUserId(userId);
		if(adverts != null)
			for(Advert advert : adverts)
				responseSet.add(convertAdvertToAdvertResponse(advert));
		return responseSet;
	}
	
	public Set<AdvertResponse> getPassiveAdverts(Integer userId) {
		Set<AdvertResponse> responseSet = new HashSet<>();
		Iterable<Advert> adverts = advertRepository.findAdvertByUserIdandAdvertStatus(userId, "Passive");
		for(Advert advert : adverts)
			responseSet.add(convertAdvertToAdvertResponse(advert));
		return responseSet;
	}
		
	public Set<AdvertResponse> getActiveAdverts(Integer userId) {
		Set<AdvertResponse> responseSet = new HashSet<>();
		Iterable<Advert> adverts = advertRepository.findAdvertByUserIdandAdvertStatus(userId, "Active");
		for(Advert advert : adverts)
			responseSet.add(convertAdvertToAdvertResponse(advert));
		return responseSet;
	}

	public Set<AdvertResponse> getAdverts() {
		List<Advert> adverts = advertRepository.findAll();
		Set<AdvertResponse> advertResponses = convertAdvertsToResponse(adverts);
		return advertResponses;
	}

	public AdvertResponse updateAdvert(AdvertRequest advertRequest) {
		Advert advert = findById(advertRequest.getAdvertId());
		if(advert.getCreatorUserId() != advertRequest.getCreatorUserId())
			throw new AdvertDoesNotBelongToThisUserException("Advert Does Not Belong To This User");
		if(advert.getAdvertStatus() != AdvertStatus.IN_REVIEW) {
			if(advertRequest.getAdvertStatus() != advert.getAdvertStatus())
			advert.setAdvertStatus(advertRequest.getAdvertStatus());
			advert.setPrice(advertRequest.getPrice());
			advert.setTitle(advertRequest.getTitle());
			advertRepository.save(advert);
			AdvertResponse response = convertAdvertToAdvertResponse(advert);
			return response;
		}
		throw new AdvertInReviewException("Advert In Review");
	}

	public void changeReviewStatus(AdvertRequest advertRequest) {
		Advert advert = findById(advertRequest.getAdvertId());
		advert.setAdvertStatus(advertRequest.getAdvertStatus());
		advertRepository.save(advert);
	}

	public Set<AdvertResponse> getAdvertsByUserIdFromUser(int userId) {
		Set<AdvertResponse> responseSet = new HashSet<>();
		Iterable<Advert> adverts = advertRepository.findAdvertByUserId(userId);
		if(adverts != null)
			for(Advert advert : adverts)
				responseSet.add(convertAdvertToAdvertResponseWithoutUser(advert));
		return responseSet;
	}


}
