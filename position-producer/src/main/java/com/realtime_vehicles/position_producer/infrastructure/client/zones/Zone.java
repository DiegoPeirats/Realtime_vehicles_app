package com.realtime_vehicles.position_producer.infrastructure.client.zones;

public class Zone {
	
private String code;
	
	private String name;
	
	private Integer area;

	public Zone(String code, String name, Integer area) {
		this.code = code;
		this.name = name;
		this.area = area;
	}

	public Zone() {
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

}
