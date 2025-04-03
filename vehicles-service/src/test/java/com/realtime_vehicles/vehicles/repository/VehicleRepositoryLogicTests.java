package com.realtime_vehicles.vehicles.repository;
import static org.mockito.Mockito.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.realtime_vehicles.vehicles.domain.entity.Vehicle;
import com.realtime_vehicles.vehicles.infrastructure.repository.VehicleRepository;

@ExtendWith(MockitoExtension.class)
public class VehicleRepositoryLogicTests {
	
	@Mock
	private VehicleRepository repository;
	
	@Test
	void shouldReturnVehiclesByZoneCode() {
		
		when(repository.findByZoneCode("zona-norte"))
			.thenReturn(List.of(new Vehicle(1L,"zona-norte","car", "Toyota")));
		
		List<Vehicle> vehicles = repository.findByZoneCode("zona-norte");
		
		assertEquals(1, vehicles.size());
		assertEquals("Toyota", vehicles.get(0).getBrand());
	}

}
