package com.realtime_vehicles.geo_zone.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="zones")
public class GeoZone {
	
	@Id
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
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
