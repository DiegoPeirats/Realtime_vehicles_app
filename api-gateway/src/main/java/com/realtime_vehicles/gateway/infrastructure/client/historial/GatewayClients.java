package com.realtime_vehicles.gateway.infrastructure.client.historial;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.realtime_vehicles.gateway.infrastructure.client.user.User;
import com.realtime_vehicles.gateway.infrastructure.client.vehicle.Vehicle;
import com.realtime_vehicles.gateway.infrastructure.client.zone.GeoZone;

@Component
public class GatewayClients {
	
	private final RestTemplate restTemplate;

 
    public GatewayClients(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Vehicle> getVehicles() {
        String url = "http://vehicles-service/vehicles-app/api/v1/vehicles";  

        ResponseEntity<List<Vehicle>> response = restTemplate.exchange(
                url, 
                org.springframework.http.HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<Vehicle>>() {}
        );

        return response.getBody();  
    }

    public List<GeoZone> getZones() {
        String url = "http://geo-zone-service/zones-app/api/v1/zones";  

        ResponseEntity<List<GeoZone>> response = restTemplate.exchange(
                url, 
                org.springframework.http.HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<List<GeoZone>>() {}
        );

        return response.getBody();  
    }

    public Historial getHistorial(String vehicleCode) {
        String url = "http://vehicles-service/vehicles-app/api/v1/historial/" + vehicleCode;  

        ResponseEntity<Historial> response = restTemplate.exchange(
                url, 
                org.springframework.http.HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<Historial>() {}
        );

        return response.getBody();  
    }

    public User getUser(Long id) {
        String url = "http://users-service/user-app/api/v1/user/" + id;  

        ResponseEntity<User> response = restTemplate.exchange(
                url, 
                org.springframework.http.HttpMethod.GET, 
                null, 
                new ParameterizedTypeReference<User>() {}
        );

        return response.getBody();  
    }
}
