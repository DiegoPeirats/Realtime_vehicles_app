package com.realtime_vehicles.vehicles.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import com.realtime_vehicles.vehicles.domain.entity.Vehicle;
import com.realtime_vehicles.vehicles.infrastructure.repository.VehicleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) 
@TestPropertySource(locations = "classpath:application-test.properties")
public class VehicleRepositoryIntegrationTests {
	
	@Autowired
	private VehicleRepository repository;
	
	@BeforeEach
	void setUp() {
		repository.save(new Vehicle(null,"zona-norte","car", "Toyota"));
		repository.save(new Vehicle(null,"zona-sur","car", "Mitsubishi"));
	}
	
	@Test
	void shouldReturnVehiclesByZoneCode() {
		List<Vehicle> vehicles = repository.findByZoneCode("zona-norte");
		
		assertThat(vehicles)
			.isNotEmpty()
			.hasSize(1)
			.allMatch(vehicle -> vehicle.getBrand().equals("Toyota"));
	}

}
