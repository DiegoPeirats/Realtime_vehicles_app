package com.realtime_vehicles.alerts.application.response;


public class AlertDTO {
	
	private Long id;
	
	private String vehicleCode;
	
	private String zoneCode;
	
	private String message;

	public AlertDTO(String vehicleCode, String zoneCode, String message) {
		this.vehicleCode = vehicleCode;
		this.zoneCode = zoneCode;
		this.message = message;
	}

	public AlertDTO(Long id, String vehicleCode, String zoneCode, String message) {
		super();
		this.id = id;
		this.vehicleCode = vehicleCode;
		this.zoneCode = zoneCode;
		this.message = message;
	}



	public AlertDTO() {
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

	@Override
	public String toString() {
		return "AlertDTO [id=" + id + ", vehicleCode=" + vehicleCode + ", zoneCode=" + zoneCode + ", message=" + message
				+ "]";
	}
}
