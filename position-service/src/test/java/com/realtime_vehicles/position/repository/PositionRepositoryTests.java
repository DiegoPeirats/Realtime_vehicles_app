package com.realtime_vehicles.position.repository;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;

import com.realtime_vehicles.position.domain.document.Position;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionRepositoryTests {
	
	@Mock  
    private PositionRepository repository;
    
    @BeforeEach
    void setUp() {

        Position position1 = new Position("1", 12.5, 50.0, "Zona Norte");
        Position position2 = new Position("2", 50.1, 20.0, "Zona Sur");


        when(repository.findFirstByVehicleCodeOrderByTimestampDesc("1"))
            .thenReturn(Mono.just(position1));

        when(repository.findAllByOrderByTimestampAsc())
            .thenReturn(Flux.just(position1, position2));

        when(repository.findAllByZoneCodeOrderByTimestampDesc("Zona Norte"))
            .thenReturn(Flux.just(position1));

        when(repository.findAllByVehicleCodeOrderByTimestampDesc("1"))
            .thenReturn(Flux.just(position1));
        
        when(repository.findFirstByVehicleCodeOrderByTimestampDesc("3"))
        .thenReturn(Mono.error(new RuntimeException("Error en la consulta")));
    }
	
	
	@Test
	@Order(1)
	public void findFirstVehicle() {
		StepVerifier.create(repository.findFirstByVehicleCodeOrderByTimestampDesc("1").log())
		.expectSubscription()
		.expectNextMatches(position -> position.getZoneCode().equals("Zona Norte"))
		.verifyComplete();
		
	}
	
	@Test
	@Order(2)
	public void findFirstVehicle_ShouldThrowException() {
	    StepVerifier.create(repository.findFirstByVehicleCodeOrderByTimestampDesc("3"))
	        .expectSubscription()
	        .expectErrorMatches(throwable -> 
	            throwable instanceof RuntimeException &&
	            throwable.getMessage().equals("Error en la consulta")
	        )
	        .verify();
	}
	
	@Test
	@Order(3)
	public void findAllByOrderByTimestampAsc() {
		StepVerifier.create(repository.findAllByOrderByTimestampAsc())
			.expectSubscription()
			.expectNextCount(2)
			.verifyComplete();
	}
	
	@Test
	@Order(4)
	public void findAllByZoneCode() {
		StepVerifier.create(repository.findAllByZoneCodeOrderByTimestampDesc("Zona Norte"))
			.expectSubscription()
			.expectNextCount(1)
			.verifyComplete();
	}
	
	@Test
	@Order(5)
	public void findAllByVehicleCode() {
		StepVerifier.create(repository.findAllByVehicleCodeOrderByTimestampDesc("1"))
			.expectSubscription()
			.expectNextCount(1)
			.verifyComplete();
	}
	

}
