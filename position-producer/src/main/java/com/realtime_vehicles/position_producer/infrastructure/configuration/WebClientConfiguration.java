package com.realtime_vehicles.position_producer.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {
	
	@Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

}
