package com.realtime_vehicles.historial.infrastructure.client;

public class Vehicle {

private Long id;
	
	private String zoneCode;
	
	private String type;
	
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
}
