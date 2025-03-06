package com.realtime_vehicles.gateway.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.realtime_vehicles.gateway.application.response.VehicleHistorial;
import com.realtime_vehicles.gateway.application.response.ZoneVehicles;
import com.realtime_vehicles.gateway.domain.GatewayService;
import com.realtime_vehicles.gateway.infrastructure.client.historial.GatewayClients;
import com.realtime_vehicles.gateway.infrastructure.client.user.User;


@Service
public class GatewayServiceImpl implements GatewayService{
	
	@Autowired
	private GatewayClients client;

	@Override
	public ResponseEntity<VehicleHistorial> getHistorial(Long vehicleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<ZoneVehicles> getZoneVehicles(String zoneCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserInfo(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
