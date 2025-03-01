package com.realtime_vehicles.position.domain.service;

import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

public interface PositionService {
	
	Mono<ServerResponse> getVehiclePosition(String vehicleCode);
	Mono<ServerResponse> getZoneVehicles(String zoneCode);

}
