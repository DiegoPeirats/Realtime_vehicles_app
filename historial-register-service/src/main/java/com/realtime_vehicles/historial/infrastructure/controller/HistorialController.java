package com.realtime_vehicles.historial.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_vehicles.historial.application.response.Historial;
import com.realtime_vehicles.historial.domain.service.HistorialService;

@RestController
@RequestMapping("api/v1")
public class HistorialController {
	
	@Autowired
	private HistorialService service;
	
	@GetMapping("/historial/{vehicleCode}")
	public ResponseEntity<Historial> getHistorial(@PathVariable String vehicleCode){
		return service.getHistorial(vehicleCode);
	}

}
