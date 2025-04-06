package com.realtime_vehicles.users.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import com.realtime_vehicles.users.domain.entity.User;
import com.realtime_vehicles.users.infrastructure.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) 
@TestPropertySource(locations = "classpath:application-test.properties")
public class UserRepositoryIntegrationTest {
	
	@Autowired
	private UserRepository repository;
	
	@BeforeEach
	void setUp() {
		repository.save(new User(null,"diego","1234", "diego@gmail.com"));
		repository.save(new User(null,"maikel","1234", "maikel@gmail.com"));
	}
	
	@Test
	void shouldReturnUserSearchingByEmail() {
		Optional<User> user = repository.findByEmail("diego@gmail.com");
		
		assertThat(user).isPresent();
		assertThat(user.get().getId()).isEqualTo(1L);
		assertThat(user.get().getEmail()).isEqualTo("diego@gmail.com");
	}

}
