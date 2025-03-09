package com.realtime_vehicles.position_producer.client;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.reactive.function.client.WebClient;

import com.realtime_vehicles.position_producer.domain.Position;
import com.realtime_vehicles.position_producer.infrastructure.client.PositionClient;
import com.realtime_vehicles.position_producer.infrastructure.client.vehicles.VehiclesClient;
import com.realtime_vehicles.position_producer.infrastructure.client.zones.ZoneClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PositionClientTests {
	
	@Mock
    private WebClient.Builder webClientBuilder;

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec<?> requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;
    
    @MockBean  
    private VehiclesClient vehiclesClient;
    
    @MockBean  
    private ZoneClient zoneClient;

    private PositionClient positionClient;
    
    @BeforeEach
    void setUp() {
        Mockito.when(webClientBuilder.baseUrl(Mockito.anyString())).thenReturn(webClientBuilder);
        Mockito.when(webClientBuilder.build()).thenReturn(webClient);

        positionClient = new PositionClient(webClientBuilder);
    }
    
    @Test
    @Order(1)
    void testGetPositionsByZone() {
        String zoneCode = "Zona Norte";
        Position position1 = new Position("1", 5.2, 6.2, "Zona Norte", Instant.now());
        Position position2 = new Position("2", 4.2, 3.2, "Zona Norte", Instant.now());

        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri("/zone/" + zoneCode)).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToFlux(Position.class)).thenReturn(Flux.just(position1, position2));

        StepVerifier.create(positionClient.getPositionsByZone(zoneCode))
            .expectNext(position1)
            .expectNext(position2)
            .verifyComplete();

        Mockito.verify(webClient, Mockito.times(1)).get();
    }
    
    @Test
    @Order(2)
    void testGetVehiclePosition() {
        Long vehicleCode = (long) 1;
        Position position1 = new Position("1", 5.2, 6.2, "Zona Norte", Instant.now());

        Mockito.when(webClient.get()).thenReturn(requestHeadersUriSpec);
        Mockito.when(requestHeadersUriSpec.uri("/vehicle/" + vehicleCode)).thenReturn(requestHeadersSpec);
        Mockito.when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        Mockito.when(responseSpec.bodyToMono(Position.class)).thenReturn(Mono.just(position1));

        StepVerifier.create(positionClient.getVehiclePosition(vehicleCode))
            .expectNext(position1)
            .verifyComplete();

        Mockito.verify(webClient, Mockito.times(1)).get();
    }

}
