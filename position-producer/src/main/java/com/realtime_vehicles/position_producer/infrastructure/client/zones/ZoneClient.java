package com.realtime_vehicles.position_producer.infrastructure.client.zones;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="geo-zone-service", path="/zone-app")
public interface ZoneClient {
	
	@GetMapping("/api/v1/zones")
	List<Zone> getZones();

}
