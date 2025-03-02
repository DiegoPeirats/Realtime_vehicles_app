package com.realtime_vehicles.alerts.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.alerts.application.response.AlertDTO;
import com.realtime_vehicles.alerts.application.response.AlertMessages;
import com.realtime_vehicles.alerts.domain.entity.Alert;
import com.realtime_vehicles.alerts.domain.service.AlertService;
import com.realtime_vehicles.alerts.infrastructure.repository.AlertRepository;
import com.realtime_vehicles.alerts.infrastructure.response.Position;

@Service
public class AlertServiceImpl implements AlertService{
	
	@Autowired
	private AlertRepository repository;
	
	@Autowired
	private WebSocketPublisher publisher;
	
	@Autowired
	private ModelMapper modelMapper;

	private Alert produceAlert(Position position) {
		if (position.getX() > 100 | position.getY() > 100) {
			Alert alert = new Alert(position.getVehicleCode(), position.getZoneCode(), AlertMessages.EXCESIVE_MOVEMENT.getMessage());
			repository.save(alert);
			
		}
		return null;
	}

	@Override
	public void sendAlert(Position position) {
		Alert alert = produceAlert(position);
		if (alert != null) {
			AlertDTO alertDTO = modelMapper.map(alert, AlertDTO.class);
			publisher.sendAlert(alertDTO);
		}
		
	}

}
