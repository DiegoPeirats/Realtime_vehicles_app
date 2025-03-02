package com.realtime_vehicles.alerts.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="alerts")
public class Alert {
	
	@Id
	@SequenceGenerator(
			name ="alerts_id",
			sequenceName = "alerts_id",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator = "alerts_id")
	private Long id;
	
	@Column(nullable = false)
	private String vehicleCode;
	
	@Column(nullable = false)
	private String zoneCode;
	
	@Column(nullable = false)
	private String message;
	
	
	public Alert() {
	}

	public Alert(String vehicleCode, String zoneCode, String message) {
		this.vehicleCode = vehicleCode;
		this.zoneCode = zoneCode;
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
