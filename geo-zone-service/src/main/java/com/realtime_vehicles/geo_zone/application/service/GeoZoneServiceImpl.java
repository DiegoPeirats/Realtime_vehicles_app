package com.realtime_vehicles.geo_zone.application.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.geo_zone.application.response.GeoZoneDTO;
import com.realtime_vehicles.geo_zone.domain.service.GeoZoneService;
import com.realtime_vehicles.geo_zone.infrastructure.repository.GeoZoneRepository;

@Service
public class GeoZoneServiceImpl implements GeoZoneService{

	@Autowired
	private GeoZoneRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<GeoZoneDTO> getByCode(String code) {
		return repository.findById(code)
				.map(zone -> modelMapper.map(zone, GeoZoneDTO.class))
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<GeoZoneDTO> getByName(String name) {
		return repository.findByName(name)
				.map(zone -> modelMapper.map(zone, GeoZoneDTO.class))
				.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<List<GeoZoneDTO>> getAll() {
		List<GeoZoneDTO> zoneList = repository.findAll().stream()
				.map(zone -> modelMapper.map(zone, GeoZoneDTO.class))
				.collect(Collectors.toList());
		
		return !zoneList.isEmpty() ?
				ResponseEntity.ok(zoneList) : ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
	}
}
