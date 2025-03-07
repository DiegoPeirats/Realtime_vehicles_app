package com.realtime_vehicles.gateway.domain;

import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.gateway.application.response.VehicleHistorial;
import com.realtime_vehicles.gateway.application.response.ZoneVehicles;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;

public interface GatewayService {
	
	ResponseEntity<VehicleHistorial> getHistorial(Long vehicleId);
	ResponseEntity<ZoneVehicles> getZoneVehicles(String zoneCode);
	ResponseEntity<User> getUserInfo(Long userId);
	ResponseEntity<User> createUser(User user);
	ResponseEntity<User> updateUser(User user);
	void deleteUser(Long userId);

}
