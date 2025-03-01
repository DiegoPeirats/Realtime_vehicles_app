package com.realtime_vehicles.api_discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ApiDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDiscoveryServiceApplication.class, args);
	}

}
