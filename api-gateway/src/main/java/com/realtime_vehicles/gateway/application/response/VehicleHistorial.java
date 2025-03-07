package com.realtime_vehicles.gateway.application.response;

import com.realtime_vehicles.gateway.infrastructure.client.historial.Historial;

public class VehicleHistorial {
	
	private String vehicleCode;
	private Historial historial;
	
	
	public VehicleHistorial(String vehicleCode, Historial historial) {
		this.vehicleCode = vehicleCode;
		this.historial = historial;
	}
	
	public String getVehicleCode() {
		return vehicleCode;
	}
	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}
	public Historial getHistorial() {
		return historial;
	}
	public void setHistorial(Historial historial) {
		this.historial = historial;
	}
	
	

}
