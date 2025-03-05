package com.realtime_vehicles.historial.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtime_vehicles.historial.domain.entity.PositionSaved;

@Repository
public interface HistorialRepository extends JpaRepository<PositionSaved, String>{
	
	List<PositionSaved> findByVehicleCode(String vehicleCode);
	
	Optional<PositionSaved> findFirstByVehicleCode(String vehicleCode);

}
