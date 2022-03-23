package com.emlakburada.user.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emlakburada.user.model.Advert;

@Repository
@Transactional
public interface AdvertRepository extends JpaRepository<Advert, Integer>{
	
	@Modifying(clearAutomatically = true)
	@Query(value = "Select * From advert a Where a.creator_user_id = :user_id", nativeQuery = true)
	Iterable<Advert> findAdvertByUserId(@Param(value = "user_id") Integer userId );
}
