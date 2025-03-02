package com.realtime_vehicles.alerts.application.service;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.alerts.application.response.AlertDTO;

@Component
public class WebSocketPublisher {
    
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAlert(AlertDTO message) {
        messagingTemplate.convertAndSend("/topic/alerts", message);
        System.out.println("📡 Mensaje enviado a WebSocket: " + message);
    }
}
