package com.realtime_vehicles.gateway.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_vehicles.gateway.application.response.VehicleHistorial;
import com.realtime_vehicles.gateway.application.response.ZoneVehicles;
import com.realtime_vehicles.gateway.application.service.GatewayServiceImpl;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;

@RestController
@RequestMapping("api/v1")
public class GatewayController {
	
	@Autowired
	private GatewayServiceImpl service;
	
	@GetMapping("/historial/{id}")
	public ResponseEntity<VehicleHistorial> getHistorial(@PathVariable Long id){
		return service.getHistorial(id);
	}
	
	@GetMapping("/zoneVehicles/{zoneCode}")
	public ResponseEntity<ZoneVehicles> getHistorial(@PathVariable String zoneCode){
		return service.getZoneVehicles(zoneCode);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id){
		return service.getUserInfo(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return service.createUser(user);
	}
	
	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Long id) {
		service.deleteUser(id);
	}

}
