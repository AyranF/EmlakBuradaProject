package com.emlakburada.user.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.emlakburada.user.dto.UserRequest;
import com.emlakburada.user.dto.UserResponse;
import com.emlakburada.user.exceptions.UserNotFoundException;
import com.emlakburada.user.model.User;
import com.emlakburada.user.repository.UserRepository;
import com.emlakburada.user.service.UserService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
	@InjectMocks
	private UserService userService;
	@Mock
	private UserRepository userRepository;
	
	@Test
	void saveNewUserUserTest() {	
		Mockito.when(userRepository.save(any())).thenReturn(prepareUser(0));
		assertDoesNotThrow(() -> {
			UserResponse response = userService.saveUser(prepareUserRequest(0));
			assertEquals(0, response.getUserId());
			assertEquals("testusername", response.getUserName());
			assertEquals("testusersurname", response.getUserSurname());
			assertEquals("testemail", response.getEMail());
		});
	}
	
	@Test
	void findNonExistingUserById() {
		Mockito.when(userRepository.findById(0)).thenReturn(Optional.empty());
		Exception e = assertThrows((UserNotFoundException.class), () -> {
			userService.findUserById(0);
		});
		assertEquals("User Not Found", e.getMessage());
	}
	
	private UserRequest prepareUserRequest(int id) {
		return UserRequest.builder()
				.userId(id)
				.password("testPass")
				.userName("testusername")
				.userSurname("testusersurname")
				.eMail("testemail")
				.build();
	}
	
	private User prepareUser(int id) {
		return User.builder()
				.userId(id)
				.password("testPass")
				.userName("testusername")
				.userSurname("testusersurname")
				.email("testemail")
				.build();
	}
	
	private UserResponse prepareUserResponse(int id) {
		return UserResponse.builder()
				.userId(id)
				.userName("testusername")
				.userSurname("testusersurname")
				.eMail("testemail")
				.build();
	}
}
