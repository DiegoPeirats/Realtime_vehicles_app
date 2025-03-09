package com.realtime_vehicles.position.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

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
import com.realtime_vehicles.position.application.service.RemoveOldPositionsService;
import com.realtime_vehicles.position.domain.document.Position;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CleanDbServiceTests {
	
	@Mock
    private PositionRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PositionServiceImpl positionService;
    
    private Position position;
    private Position position2;
    private Position position3;
    private Position position4;
    private Position position5;
    private Position position6;

    @BeforeEach
    public void setup() {
        position = new Position("1", 5.2, 6.2, "Zona Norte");
        position2 = new Position("1", 4.2, 1.2, "Zona Norte");
        position3 = new Position("1", 5.5, 8.2, "Zona Norte");
        position4 = new Position("1", 57.2, 5.2, "Zona Norte");
        position5 = new Position("1", 58.2, 23.2, "Zona Norte");
        position6 = new Position("1", 5.2, 3.2, "Zona Norte");
    }
    
    @Test
    @Order(1)
    void testRemoveRegisters() {

        BDDMockito.given(repository.findAll())
            .willReturn(Flux.just(position, position2, position3, position4, position5, position6));

        BDDMockito.given(repository.findAllByVehicleCodeOrderByTimestampDesc("1"))
            .willReturn(Flux.just(position, position2, position3, position4, position5, position6)); 

        BDDMockito.given(repository.delete(any(Position.class)))
            .willReturn(Mono.empty());  

        RemoveOldPositionsService removeOldPositionsService = new RemoveOldPositionsService(repository);

        removeOldPositionsService.periodicRemoveRegister(); 

        StepVerifier.create(repository.findAllByVehicleCodeOrderByTimestampDesc("1"))  
            .expectSubscription()
            .expectNextCount(5); 

        BDDMockito.verify(repository, BDDMockito.times(6)).delete(any(Position.class));
    }


}
