package com.realtime_vehicles.historial.domain.entity;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial")
public class PositionSaved{
	
	@Id
	@SequenceGenerator(
			name ="users_id",
			sequenceName = "users_id",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator = "users_id"
			)
	private String id;  

    private String vehicleCode;
    private double x;
    private double y;
    private String zoneCode;
    private Instant timestamp;
    
	public PositionSaved(String id, String vehicleCode, double x, double y, String zoneCode, Instant timestamp) {
		this.id = id;
		this.vehicleCode = vehicleCode;
		this.x = x;
		this.y = y;
		this.zoneCode = zoneCode;
		this.timestamp = timestamp;
	}

	public PositionSaved(String vehicleCode, double x, double y, String zoneCode, Instant timestamp) {
		this.vehicleCode = vehicleCode;
		this.x = x;
		this.y = y;
		this.zoneCode = zoneCode;
		this.timestamp = timestamp;
	}



	public PositionSaved() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
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

	@Override
	public String toString() {
		return "Position [vehicleCode=" + vehicleCode + ", x=" + x + ", y=" + y + ", zoneCode="
				+ zoneCode + ", timestamp=" + timestamp + "]";
	}

}
