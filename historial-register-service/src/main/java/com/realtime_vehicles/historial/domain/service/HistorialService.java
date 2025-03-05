package com.realtime_vehicles.historial.domain.service;

import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.historial.application.response.Historial;
import com.realtime_vehicles.historial.domain.entity.PositionSaved;
import com.realtime_vehicles.historial.infrastructure.listener.PositionReceived;

public interface HistorialService {
	
	PositionSaved savePosition(PositionReceived position);
	
	ResponseEntity<Historial> getHistorial(String vehicleCode);

}
