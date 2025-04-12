package com.realtime_vehicles.monitoring.infrastructure.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.realtime_vehicles.monitoring.application.service.MonitoringServiceImpl;
import com.realtime_vehicles.monitoring.infrastructure.response.Position;

@Component
public class PositionConsumerListener {
	
	private static final Logger log = LoggerFactory.getLogger(PositionConsumerListener.class);

	private MonitoringServiceImpl service;
	
	public PositionConsumerListener(MonitoringServiceImpl service) {
		this.service = service;
	}

	@KafkaListener(
		    topics = "position_topics",
		    groupId = "monitoring"
		)
		public void consumePosition(Position message) {
		    log.info("Recibiendo una posicion: {}", message);
		    service.sendPositions(message);
		}

}
