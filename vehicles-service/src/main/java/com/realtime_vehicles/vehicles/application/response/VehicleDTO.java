package com.realtime_vehicles.vehicles.application.response;

public class VehicleDTO {
	
	private String zone_code;
	
	private String type;
	
	private String brand;
	
	public VehicleDTO(String zone_code, String type, String brand) {
		this.zone_code = zone_code;
		this.type = type;
		this.brand = brand;
	}
	
	public VehicleDTO() {
	}

	public String getZone_code() {
		return zone_code;
	}

	public void setZone_code(String zone_code) {
		this.zone_code = zone_code;
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
		return "Vehicle [zone_code=" + zone_code + ", type=" + type + ", brand=" + brand + "]";
	}

}
