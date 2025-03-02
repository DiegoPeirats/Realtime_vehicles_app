package com.realtime_vehicles.alerts.domain.service;

import com.realtime_vehicles.alerts.infrastructure.response.Position;

public interface AlertService {
	
	void sendAlert(Position position);
	

}
