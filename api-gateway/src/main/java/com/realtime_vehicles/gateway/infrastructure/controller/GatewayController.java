package com.realtime_vehicles.gateway.infrastructure.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtime_vehicles.gateway.application.service.GatewayServiceImpl;

@RestController
@RequestMapping("api/v1")
public class GatewayController {
	
	@Autowired
	private GatewayServiceImpl service;

}
