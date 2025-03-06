package com.realtime_vehicles.monitoring.application.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.monitoring.application.publisher.WebSocketPublisher;
import com.realtime_vehicles.monitoring.domain.MonitoringService;
import com.realtime_vehicles.monitoring.infrastructure.response.Position;

import reactor.core.publisher.Flux;

@Service
public class MonitoringServiceImpl implements MonitoringService{
	
	
	private final Map<String, Position> positionMap = new ConcurrentHashMap<>();

    private Flux<Position> positionList = Flux.empty();
    
    @Autowired
    private WebSocketPublisher publisher;
	

	private Flux<Position> getPosition(Position position) {
		
		positionMap.put(position.getVehicleCode(), position);

        positionList = Flux.fromIterable(positionMap.values());

        return positionList;
	}
	

	@Override
	public void sendPositions(Position position) {
		getPosition(position);
		publisher.sendPositions(positionList);
	}

}
