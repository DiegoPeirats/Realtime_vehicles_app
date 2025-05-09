package com.realtime_vehicles.historial.application.response;

import java.time.Instant;

public class PositionDTO {
	
	private double x;
    private double y;
    private String zoneCode;
    private Instant timestamp;
    
	public PositionDTO(double x, double y, String zoneCode, Instant timestamp) {
		this.x = x;
		this.y = y;
		this.zoneCode = zoneCode;
		this.timestamp = timestamp;
	}

	public PositionDTO() {
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

}
