package com.realtime_vehicles.gateway.infrastructure.client.zone;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ZoneClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public List<GeoZone> getZones() {
        String url = "http://geo-zone-service/zones-app/api/v1/zones";  

        ResponseEntity<List<GeoZone>> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<GeoZone>>() {}
        );

        return response.getBody();  
    }

}
