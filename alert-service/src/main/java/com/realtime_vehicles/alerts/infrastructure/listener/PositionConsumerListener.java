package com.realtime_vehicles.alerts.infrastructure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.alerts.application.service.AlertServiceImpl;
import com.realtime_vehicles.alerts.infrastructure.response.Position;


@Component
public class PositionConsumerListener {
	
	private static final Logger log = LoggerFactory.getLogger(PositionConsumerListener.class);
	
	@Autowired
	private AlertServiceImpl service;
	
	@KafkaListener(
		    topics = "zona-norte-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public void consumePosition(Position message) {
		    log.info("Recibiendo un mensaje: {}", message);
		    service.sendAlert(message);
		}
	


}
