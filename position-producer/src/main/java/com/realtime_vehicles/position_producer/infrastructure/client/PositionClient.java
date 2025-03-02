package com.realtime_vehicles.position_producer.infrastructure.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.realtime_vehicles.position_producer.domain.Position;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PositionClient {
	
	private final WebClient webClient;

    public PositionClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8001/position-app/functional").build();
    }

    public Flux<Position> getPositionsByZone(String zoneCode) {
        return webClient.get()
                .uri("/zone/" + zoneCode)  
                .retrieve()
                .bodyToFlux(Position.class);
    }
    public Mono<Position> getVehiclePosition(Long vehicleCode) {
        return webClient.get()
                .uri("/vehicle/" + vehicleCode)  
                .retrieve()
                .bodyToMono(Position.class);
    }

}
