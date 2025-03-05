package com.realtime_vehicles.historial.infrastructure.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="vehicles-service", path="/vehicles-app")
public interface VehicleClient {
	
	@GetMapping("/api/v1/vehicles")
	List<Vehicle> getVehicles();

}
