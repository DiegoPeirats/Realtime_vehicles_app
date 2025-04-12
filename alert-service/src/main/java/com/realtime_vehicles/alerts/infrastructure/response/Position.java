package com.realtime_vehicles.alerts.infrastructure.response;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Position {

    private String id;  
    private String vehicleCode;
    private double x;
    private double y;
    private String zoneCode;
    private Instant timestamp;

    // Constructor con anotaciones JSON
    @JsonCreator
    public Position(@JsonProperty("id") String id, 
                    @JsonProperty("vehicleCode") String vehicleCode,
                    @JsonProperty("x") double x, 
                    @JsonProperty("y") double y,
                    @JsonProperty("zoneCode") String zoneCode, 
                    @JsonProperty("timestamp") Instant timestamp) {
        this.id = id;
        this.vehicleCode = vehicleCode;
        this.x = x;
        this.y = y;
        this.zoneCode = zoneCode;
        this.timestamp = timestamp;
    }

    // Constructor por defecto
    public Position() {}

    // Getters y setters

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("vehicleCode")
    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    @JsonProperty("x")
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @JsonProperty("y")
    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @JsonProperty("zoneCode")
    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }

    @JsonProperty("timestamp")
    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Position [vehicleCode=" + vehicleCode + ", x=" + x + ", y=" + y + ", zoneCode="
                + zoneCode + ", timestamp=" + timestamp + "]";
    }
}
