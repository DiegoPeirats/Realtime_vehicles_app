package com.realtime_vehicles.geo_zone.infrastructure.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_vehicles.geo_zone.application.response.GeoZoneDTO;
import com.realtime_vehicles.geo_zone.application.service.GeoZoneServiceImpl;

@RestController
@RequestMapping("/api/v1")
public class GeoZoneController{
	
	@Autowired
	private GeoZoneServiceImpl service;

	@GetMapping("/zones/{code}")
	public ResponseEntity<GeoZoneDTO> getByCode(@PathVariable String code) {
		return service.getByCode(code);
	}

	@GetMapping("/zones/name/{name}")
	public ResponseEntity<GeoZoneDTO> getByName(@PathVariable String name) {
		return service.getByName(name);
	}

	@GetMapping("/zones")
	public ResponseEntity<List<GeoZoneDTO>> getAll() {
		return service.getAll();
	}
	
	

}
