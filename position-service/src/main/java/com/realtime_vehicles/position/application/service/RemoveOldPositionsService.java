package com.realtime_vehicles.position.application.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.position.domain.document.Position;
import com.realtime_vehicles.position.domain.service.CleanDbService;
import com.realtime_vehicles.position.infrastructure.repository.PositionRepository;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
public class RemoveOldPositionsService implements CleanDbService{
	
	@Autowired
	private PositionRepository repository;
	
	private Flux<Position> obtenerHistorial(String vehicleCode) {
	    return repository.findAllbyVehicleCodeOrderByTimestampDesc(vehicleCode)
	                     .take(5); 
	}

	@Override
	public void removeRegister(String id) {
		
	    repository.findById(id)
	              .flatMap(position -> {
	                  String vehicleCode = position.getVehicleCode();
	                  return obtenerHistorial(vehicleCode)  
	                          .collectList() 
	                          .map(historial -> historial.stream()
	                                                     .noneMatch(pos -> pos.getVehicleCode().equals(position.getVehicleCode()) 
	                                                    		 && pos.getTimestamp().equals(position.getTimestamp())
                                                    		 		)); 
	              })
	              .filter(exists -> !exists) 
	              .flatMap(exists -> repository.deleteById(id)) 
	              .repeat()
	              .subscribe();  
	}
	
	@PostConstruct
    @Scheduled(fixedRate = 5000)  
    public void periodicRemoveRegister() {
        Flux.interval(Duration.ofSeconds(5))  
            .publishOn(Schedulers.boundedElastic())  // No bloquear el hilo principal
            .doOnNext(tick -> repository.findAllByOrderByTimestampAsc()
                .doOnNext(position -> removeRegister(position.getId())) 
                .subscribe())
            .subscribe();
    }

}
