package com.realtime_vehicles.alerts.application.response;

public enum AlertMessages {
	    
    EXCESIVE_MOVEMENT("Se ha excedido del territorio");

    private final String message;

    AlertMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
