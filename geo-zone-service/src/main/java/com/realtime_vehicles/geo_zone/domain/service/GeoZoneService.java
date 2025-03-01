package com.realtime_vehicles.geo_zone.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.geo_zone.application.response.GeoZoneDTO;

public interface GeoZoneService {
	
	ResponseEntity<GeoZoneDTO> getByCode(String code);
	ResponseEntity<GeoZoneDTO> getByName(String name);
	ResponseEntity<List<GeoZoneDTO>> getAll();

}
