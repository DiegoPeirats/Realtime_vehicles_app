package com.realtime_vehicles.alerts.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	
	@Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
