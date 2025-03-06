package com.realtime_vehicles.gateway.domain;

import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.gateway.application.response.VehicleHistorial;
import com.realtime_vehicles.gateway.application.response.ZoneVehicles;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;

public interface GatewayService {
	
	ResponseEntity<VehicleHistorial> getHistorial(Long vehicleId);
	ResponseEntity<ZoneVehicles> getZoneVehicles(String zoneCode);
	User getUserInfo(Long userId);

}
