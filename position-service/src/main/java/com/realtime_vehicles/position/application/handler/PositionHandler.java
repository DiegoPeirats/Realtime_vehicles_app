package com.realtime_vehicles.position.application.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.realtime_vehicles.position.application.service.PositionServiceImpl;

import reactor.core.publisher.Mono;

@Component
public class PositionHandler {
	
	@Autowired
	private PositionServiceImpl service;
	
	public Mono<ServerResponse> getVehiclePosition(ServerRequest request) {
	    String vehicleCode = request.pathVariable("vehicleCode");
	    return service.getVehiclePosition(vehicleCode);
	}

	public Mono<ServerResponse> getZoneVehicles(ServerRequest request) {
	    String zoneCode = request.pathVariable("zoneCode");

	    return service.getZoneVehicles(zoneCode);
	}

	
	
}
