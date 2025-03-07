package com.realtime_vehicles.gateway.infrastructure.client.vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class VehicleClient {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Vehicle> getVehicles() {
        String url = "http://vehicles-service/vehicles-app/api/v1/vehicles";  

        ResponseEntity<List<Vehicle>> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<Vehicle>>() {}
        );

        return response.getBody();  
    }

}
