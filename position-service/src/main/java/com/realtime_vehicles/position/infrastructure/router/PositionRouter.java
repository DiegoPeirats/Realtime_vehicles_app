package com.realtime_vehicles.position.infrastructure.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.realtime_vehicles.position.application.handler.PositionHandler;

@Configuration
public class PositionRouter {
    
	@Bean
    public RouterFunction<ServerResponse> routes(PositionHandler handler) {

    	return RouterFunctions
    			.route(RequestPredicates.GET("/functional/vehicle/{vehicleCode}"), handler::getVehiclePosition)
    			.andRoute(RequestPredicates.GET("/functional/zone/{zoneCode}"), handler::getZoneVehicles);
    }

}
