package com.realtime_vehicles.position.application.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.realtime_vehicles.position.application.response.PositionDTO;
import com.realtime_vehicles.position.domain.service.PositionService;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PositionServiceImpl implements PositionService{
	
	@Autowired
	private PositionRepository repository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	private Mono<ServerResponse> response404 = ServerResponse.notFound().build();
	private Mono<ServerResponse> response406 = ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build();

	@Override
	public Mono<ServerResponse> getVehiclePosition(String vehicleCode) {
		
		Mono<PositionDTO> dtoResponse = repository.findFirstByVehicleCodeOrderByTimestampDesc(vehicleCode)
				.flatMap(pos -> Mono.just(modelMapper.map(pos, PositionDTO.class)));
		
		return dtoResponse
				.flatMap(pos -> 
			        ServerResponse.ok()
			        .contentType(MediaType.APPLICATION_JSON)
			        .bodyValue(pos))
				.switchIfEmpty(response404);
	}

	@Override
	public Mono<ServerResponse> getZoneVehicles(String zoneCode) {
		Flux<PositionDTO> list = repository.findAllByZoneCodeOrderByTimestampDesc(zoneCode)
									.flatMap(pos -> Mono.just(modelMapper.map(pos, PositionDTO.class)));
		
		return ServerResponse.ok()
		        .contentType(MediaType.APPLICATION_JSON)
		        .body(list, PositionDTO.class)
		        .switchIfEmpty(response406);
	}

}
