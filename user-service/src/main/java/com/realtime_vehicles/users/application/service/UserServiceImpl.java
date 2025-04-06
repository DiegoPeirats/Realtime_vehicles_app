package com.realtime_vehicles.users.application.service;

import java.util.Optional;

import org.hibernate.StaleObjectStateException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.users.application.response.UserDTO;
import com.realtime_vehicles.users.domain.entity.User;
import com.realtime_vehicles.users.domain.service.UserService;
import com.realtime_vehicles.users.infrastructure.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<UserDTO> getById(Long id) {
		
		return repository.findById(id)
				.map(user -> modelMapper.map(user, UserDTO.class))
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<UserDTO> getByEmail(String email) {
		
		return repository.findByEmail(email)
				.map(user -> modelMapper.map(user, UserDTO.class))
				.map(ResponseEntity::ok)
				.orElseGet(()-> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Object> registerUser(User user) {
		
		try {
			Optional<User> userFound = repository.findByEmail(user.getEmail());
			if(userFound.isPresent()) 
				return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body("el email ya está en uso");
			User userResponse = repository.save(user);
			return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.map(userResponse, UserDTO.class));
		}catch (DataIntegrityViolationException | StaleObjectStateException e) {
	        return ResponseEntity
	        		.status(HttpStatus.BAD_REQUEST)
					.body(e);
	    }
	}

	@Override
	public ResponseEntity<?> updateUser(User user, Long id) {
		try {
			if (repository.findById(id).isEmpty()) 
				return ResponseEntity
						.status(HttpStatus.NOT_FOUND)
						.body("No se encontró el usuario");
			user.setId(id);
			User userResponse = repository.save(user);
			return ResponseEntity.ok(modelMapper.map(userResponse, UserDTO.class));
		}catch(DataIntegrityViolationException  e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@Override
	public ResponseEntity<Object> deleteUser(Long id) {
		try {
			repository.deleteById(id);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("Eliminado corréctamente");
		}catch(Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	

}
