package com.realtime_vehicles.gateway.application.response;

import java.util.List;

import com.realtime_vehicles.gateway.infrastructure.client.vehicle.Vehicle;

public class ZoneVehicles {
	
	private String zoneName;
	private List<Vehicle> vehicles;
	
	public ZoneVehicles(String zoneName, List<Vehicle> vehicles) {
		this.zoneName = zoneName;
		this.vehicles = vehicles;
	}

	public ZoneVehicles() {
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

}
