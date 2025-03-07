package com.realtime_vehicles.gateway.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.gateway.application.response.VehicleHistorial;
import com.realtime_vehicles.gateway.application.response.ZoneVehicles;
import com.realtime_vehicles.gateway.domain.GatewayService;
import com.realtime_vehicles.gateway.infrastructure.client.GatewayClients;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;
import java.util.stream.Collectors;


@Service
public class GatewayServiceImpl implements GatewayService{
	
	@Autowired
	private GatewayClients client;

	@Override
	public ResponseEntity<VehicleHistorial> getHistorial(Long vehicleId) {
		if (client.getHistorial(vehicleId.toString()) == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new VehicleHistorial(
				vehicleId.toString(), 
				client.getHistorial(vehicleId.toString())));
	}

	@Override
	public ResponseEntity<ZoneVehicles> getZoneVehicles(String zoneCode) {
		if (client.getVehicles().isEmpty()) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(new ZoneVehicles(
				zoneCode, 
				client.getVehicles().stream()
					.filter(vehicle -> vehicle.getZone_code().equalsIgnoreCase(zoneCode))
					.collect(Collectors.toList())));
	}

	@Override
	public ResponseEntity<User> getUserInfo(Long userId) {
		if (client.getUser(userId) == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(client.getUser(userId));
	}

	@Override
	public ResponseEntity<User> createUser(User user) {
		if (client.createUser(user) == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(client.updateUser(user));
	}

	@Override
	public ResponseEntity<User> updateUser(User user) {
		if (client.updateUser(user) == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(client.updateUser(user));
	}

	@Override
	public void deleteUser(Long userId) {
		client.deleteUser(userId);
	}

}
