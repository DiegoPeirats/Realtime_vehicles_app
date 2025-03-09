package com.realtime_vehicles.position.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.realtime_vehicles.position.application.response.PositionDTO;
import com.realtime_vehicles.position.application.service.PositionServiceImpl;
import com.realtime_vehicles.position.domain.document.Position;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;
import static org.assertj.core.api.Assertions.assertThat;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionServiceTests {
    
    @Mock
    private PositionRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PositionServiceImpl positionService;
    
    private Position position;
    private PositionDTO positionDTO;

    @BeforeEach
    public void setup() {
        position = new Position("1", 5.2, 6.2, "Zona Norte");
        positionDTO = new PositionDTO("1", 5.2, 6.2, "Zona Norte", position.getTimestamp());

        BDDMockito.lenient().when(modelMapper.map(position, PositionDTO.class))
            .thenReturn(positionDTO);
    }
    
    @Test
    @Order(1)
    void testFindPositionByVehicleCode_Success() {

        BDDMockito.given(repository.findFirstByVehicleCodeOrderByTimestampDesc("1"))
            .willReturn(Mono.just(position));

        Mono<ServerResponse> serverResponseMono = positionService.getVehiclePosition("1");

        StepVerifier.create(serverResponseMono)
            .expectSubscription()
            .consumeNextWith(response -> {
                assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
            })
            .verifyComplete();
    }

    @Test
    @Order(2)
    void testFindPositionByVehicleCode_NotFound() {
    	
        BDDMockito.given(repository.findFirstByVehicleCodeOrderByTimestampDesc("1"))
            .willReturn(Mono.empty());

        Mono<ServerResponse> serverResponseMono = positionService.getVehiclePosition("1");

        StepVerifier.create(serverResponseMono)
            .expectSubscription()
            .consumeNextWith(response -> {
                assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            })
            .verifyComplete();
    }
    
    @Test
    @Order(3)
    void testFindAllPositionsByZoneCode_Success() {
    	
        BDDMockito.given(repository.findAllByZoneCodeOrderByTimestampDesc("Zone Norte"))
            .willReturn(Flux.just(position));
        
        Mono<ServerResponse> serverResponseMono = positionService.getZoneVehicles("Zone Norte");
        
        StepVerifier.create(serverResponseMono)
        	.expectSubscription()
            .consumeNextWith(response -> {
                assertThat(response.statusCode()).isEqualTo(HttpStatus.OK);
            })
            .verifyComplete();
    }

    @Test
    @Order(4)
    void testFindAllPositionsByZoneCode_NotFound() {
    	
        BDDMockito.given(repository.findAllByZoneCodeOrderByTimestampDesc("Zone Norte"))
            .willReturn(Flux.empty());

        Mono<ServerResponse> serverResponseMono = positionService.getZoneVehicles("Zone Norte");

        StepVerifier.create(serverResponseMono)
            .expectSubscription()
            .consumeNextWith(response -> {
                assertThat(response.statusCode()).isEqualTo(HttpStatus.NOT_ACCEPTABLE);
            })
            .verifyComplete();
    }
}

