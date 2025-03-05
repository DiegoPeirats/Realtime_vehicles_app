package com.realtime_vehicles.historial.application.response;

import java.time.LocalDate;
import java.util.List;

public class Historial{
	
	private String vehicleCode;
	
	private List<PositionDTO> positions;
	
	private LocalDate date;

	public Historial(String vehicleCode, List<PositionDTO> positions, LocalDate date) {
		this.vehicleCode = vehicleCode;
		this.positions = positions;
		this.date = date;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public List<PositionDTO> getPositions() {
		return positions;
	}

	public void setPositions(List<PositionDTO> positions) {
		this.positions = positions;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
