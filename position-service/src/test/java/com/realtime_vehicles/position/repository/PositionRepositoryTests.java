package com.realtime_vehicles.position.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.junit.jupiter.api.Order;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PositionRepositoryTests {
	
	@Autowired
	private PositionRepository repository;
	
	@Autowired
	private ReactiveMongoOperations mongoOperations;
	
	
	@Test
	@Order(1)
	public void findFirstVehicle() {
		StepVerifier.create(repository.findFirstByVehicleCodeOrderByTimestampDesc("1").log())
		.expectSubscription()
		.expectNextMatches(position -> position.getZoneCode().equals("Zona Norte"))
		.verifyComplete();
		
	}
	
	

}
