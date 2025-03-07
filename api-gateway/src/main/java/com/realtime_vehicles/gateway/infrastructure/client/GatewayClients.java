package com.realtime_vehicles.gateway.infrastructure.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.gateway.infrastructure.client.historial.Historial;
import com.realtime_vehicles.gateway.infrastructure.client.historial.HistorialClient;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;
import com.realtime_vehicles.gateway.infrastructure.client.user.UserClient;
import com.realtime_vehicles.gateway.infrastructure.client.vehicle.Vehicle;
import com.realtime_vehicles.gateway.infrastructure.client.vehicle.VehicleClient;
import com.realtime_vehicles.gateway.infrastructure.client.zone.GeoZone;
import com.realtime_vehicles.gateway.infrastructure.client.zone.ZoneClient;

@Component
public class GatewayClients {
	
	@Autowired
	private UserClient userClient;
	
	@Autowired
	private VehicleClient vehicleClient;
	
	@Autowired
	private HistorialClient historialClient;
	
	@Autowired
	private ZoneClient ZoneClient;


    public List<Vehicle> getVehicles() {
        return vehicleClient.getVehicles();  
    }

    public List<GeoZone> getZones() {
        return ZoneClient.getZones();  
    }

    public Historial getHistorial(String vehicleCode) {
        return historialClient.getHistorial(vehicleCode);  
    }

    public User getUser(Long id) {
    	return userClient.getUser(id);  
    }

    public User createUser(User user) {
    	return userClient.createUser(user);  
    }

    public User updateUser(User user) {
    	return userClient.updateUser(user); 
    }

    public void deleteUser(Long id) {
    	userClient.deleteUser(id); }
}
