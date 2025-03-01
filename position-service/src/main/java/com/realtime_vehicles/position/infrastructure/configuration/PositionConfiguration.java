package com.realtime_vehicles.position.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PositionConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
