package com.realtime_vehicles.users.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_vehicles.users.application.response.UserDTO;
import com.realtime_vehicles.users.application.service.UserServiceImpl;
import com.realtime_vehicles.users.domain.entity.User;

@RestController
@RequestMapping("api/v1/")
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@GetMapping("users/{id}")
	public ResponseEntity<UserDTO> getById(@PathVariable Long id){
		return service.getById(id);
	}
	
	@GetMapping("users/email/{email}")
	public ResponseEntity<UserDTO> getByEmail(@PathVariable String email){
		return service.getByEmail(email);
	}
	
	@PostMapping("users")
	public ResponseEntity<UserDTO> registerUser(@RequestBody User user){
		return service.registerUser(user);
	}
	
	@PutMapping("users/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user, @PathVariable Long id){
		return service.updateUser(user, id);
	}
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Long id){
		return service.deleteUser(id);
	}

}
