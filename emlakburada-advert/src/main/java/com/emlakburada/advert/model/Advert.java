package com.emlakburada.advert.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import com.emlakburada.advert.model.enums.AdvertStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Advert {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer advertId;
	private String title;
	private Integer creatorUserId;
	@CreationTimestamp
	private Date creationTimeStamp;
	private BigDecimal price;
	@Builder.Default
	@Enumerated(EnumType.STRING)
	private AdvertStatus advertStatus = AdvertStatus.IN_REVIEW;
	
	public Integer getAdvertId() {
		return advertId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Integer getCreatorUserId() {
		return creatorUserId;
	}
	
	public void setTitle(String title) {
		if(title != null)
			this.title = title;
	}
	
	public Date getCreationTimeStamp() {
		return creationTimeStamp;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	
	public void setPrice(BigDecimal price) {
		if(price != null)
			this.price = price;
	}
	
	public AdvertStatus getAdvertStatus() {
		return advertStatus;
	}
	
	public void setAdvertStatus(AdvertStatus advertStatus) {
		if(advertStatus != null)
			this.advertStatus = advertStatus;
	}
}
	
