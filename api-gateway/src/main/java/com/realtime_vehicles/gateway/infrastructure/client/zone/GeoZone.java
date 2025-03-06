package com.realtime_vehicles.gateway.infrastructure.client.zone;

public class GeoZone {
	
private String code;
	
	private String name;
	
	private Integer area;

	public GeoZone(String code, String name, Integer area) {
		this.code = code;
		this.name = name;
		this.area = area;
	}

	public GeoZone() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArea() {
		return area;
	}

	public void setArea(Integer area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "GeoZone [code=" + code + ", name=" + name + ", area=" + area + "]";
	}

}
