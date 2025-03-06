package com.realtime_vehicles.monitoring.infrastructure.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.realtime_vehicles.monitoring.infrastructure.response.Position;

import reactor.core.publisher.Flux;


@Controller
public class WSMonitoringController {
	
	@MessageMapping("/monitoring")
    @SendTo("/position/data")
    public Flux<Position> sendData(Flux<Position> message) {
        return message; 
    }


}
