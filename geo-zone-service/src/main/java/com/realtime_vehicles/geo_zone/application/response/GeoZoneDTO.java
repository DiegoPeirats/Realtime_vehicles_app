package com.realtime_vehicles.geo_zone.application.response;

public class GeoZoneDTO {

	private String code;
	
	private String name;
	
	private Integer area;

	public GeoZoneDTO(String code, String name, Integer area) {
		this.code = code;
		this.name = name;
		this.area = area;
	}

	public GeoZoneDTO() {
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
