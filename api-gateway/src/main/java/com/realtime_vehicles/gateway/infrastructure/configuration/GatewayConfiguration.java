package com.realtime_vehicles.gateway.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GatewayConfiguration {
	
	@Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
