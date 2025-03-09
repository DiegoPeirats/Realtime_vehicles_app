package com.realtime_vehicles.position.handler;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.realtime_vehicles.position.application.handler.PositionHandler;
import com.realtime_vehicles.position.application.response.PositionDTO;
import com.realtime_vehicles.position.application.service.PositionServiceImpl;

import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PositionHandlerTests {
	
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PositionServiceImpl service; 

    @InjectMocks
    private PositionHandler handler;

    private final PositionDTO position = new PositionDTO("1", 53.0, 20.0, "Zona Sur", Instant.now());
    
    
    @Test
    @Order(1)
    void testGetVehiclePosition() {
    	BDDMockito.lenient().when(service.getVehiclePosition("1"))
        .thenReturn(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(position));

        webTestClient.get().uri("/functional/vehicle/1")
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.vehicleCode").isEqualTo("1")
            .jsonPath("$.zoneCode").isEqualTo("Zona Sur");
    }
    
    @Test
    @Order(2)
    void testGetZoneVehicles(){
    	BDDMockito.lenient().when(service.getZoneVehicles("Zona Sur")).thenReturn(ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(Flux.just(position), PositionDTO.class)  
        );

        webTestClient.get()
            .uri("/functional/zone/Zona Sur")
            .exchange()
            .expectStatus().isOk()  
            .expectBodyList(PositionDTO.class)  
            .hasSize(1) 
            .value(list -> {
                PositionDTO received = list.get(0);
                assertEquals(position.getVehicleCode(), received.getVehicleCode());
                assertEquals(position.getX(), received.getX());
                assertEquals(position.getY(), received.getY());
                assertEquals(position.getZoneCode(), received.getZoneCode());
            }); 
    }


}
