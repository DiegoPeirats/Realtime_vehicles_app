package com.realtime_vehicles.position.application.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.position.domain.service.CleanDbService;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;

import jakarta.annotation.PostConstruct;

@Service
public class RemoveOldPositionsService implements CleanDbService{
	
	private PositionRepository repository;
	
	public RemoveOldPositionsService(PositionRepository repository) {
		this.repository = repository;
	}

	@PostConstruct
	@Scheduled(fixedRate = 5000) 
	@Override 
	public void periodicRemoveRegister() {
	    repository.findAll()
	        .flatMap(position -> repository.findAllByVehicleCodeOrderByTimestampDesc(position.getVehicleCode())
	            .skip(5)
	            .next()  
	            .flatMap(repository::delete))
	        .subscribe();
	}

}
