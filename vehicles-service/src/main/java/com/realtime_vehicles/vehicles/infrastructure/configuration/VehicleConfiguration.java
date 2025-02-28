package com.realtime_vehicles.vehicles.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfiguration {
	
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
