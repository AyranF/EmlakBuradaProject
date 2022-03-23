package com.emlakburada.auth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emlakburada.auth.entity.User;

public interface AuthRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

}
