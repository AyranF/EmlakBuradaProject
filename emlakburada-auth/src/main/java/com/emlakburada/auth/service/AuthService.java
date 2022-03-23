package com.emlakburada.auth.service;

import org.springframework.stereotype.Service;

import com.emlakburada.auth.dto.AuthRequest;
import com.emlakburada.auth.dto.AuthResponse;
import com.emlakburada.auth.entity.User;
import com.emlakburada.auth.exception.UserNotFoundException;
import com.emlakburada.auth.exception.UserPasswordNotValidException;
import com.emlakburada.auth.repository.AuthRepository;
import com.emlakburada.auth.util.JwtUtil;
import com.emlakburada.auth.util.UserUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {

	private final AuthRepository authRepository;

	private final JwtUtil jwtUtil;

	public AuthResponse getToken(AuthRequest request) throws UserPasswordNotValidException {
		User user = authRepository.findByEmail(request.getEmail()).get();

		if (!UserUtil.isValidPassword(user.getPassword(), request.getPassword())) {
			log.error("User's password not valid " + request.getEmail());
			throw new UserPasswordNotValidException("User's password not valid");
		}
		return new AuthResponse(jwtUtil.generateToken(user));
	}

}
