package com.realtime_vehicles.gateway.infrastructure.client.historial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HistorialClient {
	
	@Autowired
	private RestTemplate restTemplate;

    public Historial getHistorial(String vehicleCode) {
        String url = "http://vehicles-service/vehicles-app/api/v1/historial/" + vehicleCode;  

        ResponseEntity<Historial> response = restTemplate.exchange(
                url, 
                HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<Historial>() {}
        );

        return response.getBody();  
    }

}
