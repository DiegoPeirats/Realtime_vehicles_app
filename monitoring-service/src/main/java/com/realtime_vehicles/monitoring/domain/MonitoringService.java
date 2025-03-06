package com.realtime_vehicles.monitoring.domain;

import com.realtime_vehicles.monitoring.infrastructure.response.Position;

import reactor.core.publisher.Flux;

public interface MonitoringService {
	
	void sendPositions(Position position);
	

}
