package com.realtime_vehicles.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.users.application.response.UserDTO;
import com.realtime_vehicles.users.application.service.UserServiceImpl;
import com.realtime_vehicles.users.domain.entity.User;
import com.realtime_vehicles.users.infrastructure.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTests {
	
	@Mock
	private UserRepository repository;
	
	@Mock
    private ModelMapper modelMapper;
	
	@InjectMocks
	private UserServiceImpl service;
	
	private User user;
	private UserDTO userDTO;
	
	@BeforeEach
	void setup() {
		user = new User(1L, "diego", "1234", "diego@gmail.com");
		userDTO = new UserDTO(1L, "diego", "diego@gmail.com");
		
		BDDMockito.lenient().when(modelMapper.map(user, UserDTO.class)).thenReturn(userDTO);
	}
	
	@Test
	@Order(1)
	void shouldReturnUserDTOById() {
		when(repository.findById(1L)).thenReturn(Optional.of(user));
		
		ResponseEntity<UserDTO> response = service.getById(1L);
		
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(1L, response.getBody().getId());
	}
	
	@Test
	@Order(2)
	void shouldReturnNotFoundException() {
		when(repository.findById(2L)).thenReturn(Optional.empty());
		
		ResponseEntity<UserDTO> response = service.getById(2L);
		
		assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
		assertNull(response.getBody());
	}
	
	@Test
	@Order(3)
	void shouldReturnUserDTOAfterRegister() {
		when(repository.findByEmail("diego@gmail.com")).thenReturn(Optional.empty());
		when(repository.save(user)).thenReturn(user);
		
		ResponseEntity<?> response = service.registerUser(user);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertTrue(response.getBody() instanceof UserDTO);
		assertEquals("diego@gmail.com", ((UserDTO) response.getBody()).getEmail());
	}
	
	@Test
	@Order(4)
	void shouldReturnBadRequestIfEmailExists() {
		when(repository.findByEmail("diego@gmail.com")).thenReturn(Optional.of(user));
		
		ResponseEntity<?> response = service.registerUser(user);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("el email ya está en uso", response.getBody());
	}
	
	@Test
	@Order(5)
	void shouldReturnBadRequestIfDataIntegrityViolationException() {
		when(repository.findByEmail("diego@gmail.com")).thenReturn(Optional.empty());
		when(repository.save(user)).thenThrow(DataIntegrityViolationException.class);
		
		ResponseEntity<?> response = service.registerUser(user);
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertTrue(response.getBody() instanceof DataIntegrityViolationException);
	}
	
	@Test
	@Order(6)
	void shouldReturnUserDTOAfterUpdate() {
		when(repository.findById(1L)).thenReturn(Optional.of(user));
		when(repository.save(user)).thenReturn(user);
		
		ResponseEntity<?> response = service.updateUser(user, 1L);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof UserDTO);
		assertEquals(1L, ((UserDTO) response.getBody()).getId());
        
	}

}
