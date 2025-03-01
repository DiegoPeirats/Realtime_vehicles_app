package com.realtime_vehicles.position.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.realtime_vehicles.position.domain.document.Position;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PositionRepository extends ReactiveMongoRepository<Position, String>{
	
	Mono<Position> findFirstByVehicleCodeOrderByTimestampDesc(String vehicleCode);
	Flux<Position> findAllByZoneCodeOrderByTimestampDesc(String zoneCode);

}
