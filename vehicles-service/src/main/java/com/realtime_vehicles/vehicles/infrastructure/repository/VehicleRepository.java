package com.realtime_vehicles.vehicles.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtime_vehicles.vehicles.domain.entity.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long>{
	
	List<Vehicle> findByZoneCode(String zoneCode);

}
