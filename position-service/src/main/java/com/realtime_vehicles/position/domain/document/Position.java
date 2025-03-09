package com.realtime_vehicles.position.domain.document;
import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "positions")
public class Position {

    @Id
    private String id;  

    private String vehicleCode;
    private double x;
    private double y;
    private String zoneCode;
    private Instant timestamp;

    
    public Position(String vehicleCode, double x, double y, String zoneCode) {
        this.vehicleCode = vehicleCode;
        this.x = x;
        this.y = y;
        this.zoneCode = zoneCode;
        this.timestamp = Instant.now();
        this.id = generateId(vehicleCode, this.timestamp);
    }

    public Position() {
	}

	private String generateId(String codigoVehiculo, Instant timestamp) {
        return codigoVehiculo + "_" + timestamp.toEpochMilli(); 
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
		return "Position [id=" + id + ", vehicleCode=" + vehicleCode + ", x=" + x + ", y=" + y + ", zoneCode="
				+ zoneCode + ", timestamp=" + timestamp + "]";
	}
	
}
