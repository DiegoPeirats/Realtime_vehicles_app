package com.realtime_vehicles.vehicles.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="vehicles")
public class Vehicle {
	
	@Id
	@SequenceGenerator(
			name ="vehicles_id",
			sequenceName = "vehicles_id",
			allocationSize = 1
			)
	@GeneratedValue(
			strategy= GenerationType.SEQUENCE,
			generator = "vehicles_id"
			)
	private Long id;
	
	@Column(nullable=false)
	private String zoneCode;
	
	@Column(nullable=false)
	private String type;
	
	@Column(nullable=false)
	private String brand;
	
	
	public Vehicle(Long id, String zone_code, String type, String brand) {
		this.id = id;
		this.zoneCode = zone_code;
		this.type = type;
		this.brand = brand;
	}
	
	public Vehicle(String zone_code, String type, String brand) {
		this.zoneCode = zone_code;
		this.type = type;
		this.brand = brand;
	}
	
	public Vehicle() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZone_code() {
		return zoneCode;
	}

	public void setZone_code(String zone_code) {
		this.zoneCode = zone_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", zone_code=" + zoneCode + ", type=" + type + ", brand=" + brand + "]";
	}

}
