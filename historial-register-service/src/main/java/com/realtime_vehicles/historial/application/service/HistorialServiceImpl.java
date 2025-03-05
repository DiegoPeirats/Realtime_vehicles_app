package com.realtime_vehicles.historial.application.service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.historial.application.response.Historial;
import com.realtime_vehicles.historial.application.response.PositionDTO;
import com.realtime_vehicles.historial.domain.entity.PositionSaved;
import com.realtime_vehicles.historial.domain.service.HistorialService;
import com.realtime_vehicles.historial.infrastructure.listener.PositionReceived;
import com.realtime_vehicles.historial.infrastructure.repository.HistorialRepository;

@Service
public class HistorialServiceImpl implements HistorialService{
	
	@Autowired
	private HistorialRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;

	private PositionReceived selectPositionToSave(PositionReceived position) {
		Optional<PositionSaved> posSaved = repository.findFirstByVehicleCode(position.getVehicleCode());
		if (posSaved.isEmpty()) return null;
		Duration duration = Duration.between(posSaved.get().getTimestamp(), position.getTimestamp());
		if (duration.toMinutes() >= 30) return position;
		return null;		
	}
	
	@Override
	public PositionSaved savePosition(PositionReceived position) {
		PositionReceived posSelected = selectPositionToSave(position);
		if (posSelected == null) return null;
		return repository.save(modelMapper.map(posSelected, PositionSaved.class));
	}

	private List<PositionDTO> getPositions(String vehicleCode) {
		return repository.findByVehicleCode(vehicleCode)
				.stream()
				.map(pos -> modelMapper.map(pos, PositionDTO.class))
				.limit(3)
				.collect(Collectors.toList());
	}

	@Override
	public ResponseEntity<Historial> getHistorial(String vehicleCode) {
		
		if (getPositions(vehicleCode).isEmpty()) return ResponseEntity.noContent().build();
		
		return ResponseEntity.ok(new Historial(vehicleCode, getPositions(vehicleCode), LocalDate.now()));
	}

}
