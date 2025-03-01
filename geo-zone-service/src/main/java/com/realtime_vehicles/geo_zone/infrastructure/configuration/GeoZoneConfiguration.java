package com.realtime_vehicles.geo_zone.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeoZoneConfiguration {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
