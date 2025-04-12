package com.realtime_vehicles.historial.infrastructure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.historial.application.service.HistorialServiceImpl;

@Component
public class PositionConsumerListener {
	
	private static final Logger log = LoggerFactory.getLogger(PositionConsumerListener.class);
	
	@Autowired
	private HistorialServiceImpl service;
	
	@KafkaListener(
			groupId = "historial", 
			topics = "position_topics")
	public void consumePosition(PositionReceived message) {
		log.info("Recibiendo un mensaje: {}", message);
		service.savePosition(message);
	}

}
