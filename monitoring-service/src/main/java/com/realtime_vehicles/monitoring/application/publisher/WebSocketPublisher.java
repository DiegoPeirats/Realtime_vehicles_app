package com.realtime_vehicles.monitoring.application.publisher;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.monitoring.infrastructure.response.Position;

import reactor.core.publisher.Flux;


@Component
public class WebSocketPublisher {
    
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketPublisher(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendPositions(Flux<Position> messageFlux) {
        messageFlux.subscribe(
            position -> {
                messagingTemplate.convertAndSend("/positions/data", position);
                System.out.println("📡 Mensaje enviado a WebSocket: " + position);
            },
            error -> System.err.println("❌ Error enviando mensaje: " + error),
            () -> System.out.println("✅ Flujo de posiciones completado.")
        );
    }
}
