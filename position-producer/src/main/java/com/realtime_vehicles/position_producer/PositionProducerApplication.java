package com.realtime_vehicles.position_producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PositionProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PositionProducerApplication.class, args);
	}

}
