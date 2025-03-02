package com.realtime_vehicles.position_producer.infrastructure.client.vehicles;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="vehicles-service", path="/vehicles-app")
public interface VehiclesClient {
	
	@GetMapping("/api/v1/vehicles")
	List<Vehicle> getVehicles();

}

