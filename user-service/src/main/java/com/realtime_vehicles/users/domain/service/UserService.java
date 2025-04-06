package com.realtime_vehicles.users.domain.service;

import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.users.application.response.UserDTO;
import com.realtime_vehicles.users.domain.entity.User;

public interface UserService {
	
	ResponseEntity<UserDTO> getById(Long id);
	
	ResponseEntity<UserDTO> getByEmail(String email);
	
	ResponseEntity<?> registerUser (User user);

	ResponseEntity<?> updateUser (User user, Long id);
	
	ResponseEntity<Object> deleteUser (Long id);

}
