package com.realtime_vehicles.geo_zone.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.realtime_vehicles.geo_zone.domain.entity.GeoZone;

public interface GeoZoneRepository extends JpaRepository<GeoZone, String>{
	
	Optional<GeoZone> findByName(String name);

}
