package com.realtime_vehicles.historial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HistorialRegisterServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialRegisterServiceApplication.class, args);
	}

}
