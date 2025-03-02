package com.realtime_vehicles.position_producer.domain;
import reactor.core.publisher.Flux;

public interface PositionService {
	
	Flux<Position> getZonesPositions();
	
	Flux<Position> getVehiclesPositions();

}
