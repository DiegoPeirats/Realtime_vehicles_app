package com.realtime_vehicles.position_producer.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.position_producer.domain.Position;
import com.realtime_vehicles.position_producer.domain.PositionService;
import com.realtime_vehicles.position_producer.infrastructure.client.PositionClient;
import com.realtime_vehicles.position_producer.infrastructure.client.vehicles.VehiclesClient;
import com.realtime_vehicles.position_producer.infrastructure.client.zones.ZoneClient;

import reactor.core.publisher.Flux;


@Service
public class PositionServiceImpl  implements PositionService{
	
	@Autowired
	private ZoneClient zoneClient;
	
	@Autowired
	private VehiclesClient vehiclesClient;
	
	@Autowired
	private PositionClient positionClient;

	@Cacheable("getVehiclesCodes")
	private List<Long> getVehiclesCodes (){
		return vehiclesClient.getVehicles().stream()
				.map(vehicle -> vehicle.getId())
				.collect(Collectors.toList());
	}
	
	@Cacheable("getZonesCodes")
	private List<String> getZonesCodes(){
		return zoneClient.getZones().stream()
				.map(zone -> zone.getCode())
				.collect(Collectors.toList());
	}
	
	@Override
	public Flux<Position> getZonesPositions() {
	    return Flux.fromIterable(getZonesCodes())
	               .flatMap(positionClient::getPositionsByZone); 
	}

	@Override
	public Flux<Position> getVehiclesPositions() {
	    return Flux.fromIterable(getVehiclesCodes()) 
	               .flatMap(positionClient::getVehiclePosition); 
	}

}
