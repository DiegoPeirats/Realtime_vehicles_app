package com.realtime_vehicles.alerts.infrastructure.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.realtime_vehicles.alerts.application.response.AlertDTO;

@Controller
public class WSAlertController {
	
	@MessageMapping("/alert")
    @SendTo("/topic/alerts")
    public AlertDTO sendAlert(AlertDTO message) {
        return message; 
    }
	
	//falta dividir la respuesta en mas metodos

}
