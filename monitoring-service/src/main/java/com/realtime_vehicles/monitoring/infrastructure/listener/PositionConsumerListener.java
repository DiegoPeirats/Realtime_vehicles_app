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
	
	@Autowired
	private MonitoringServiceImpl service;
	
	@KafkaListener(
		    topics = "zona-norte-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public void zonaNorte(Position message) {
		    log.info("ZONA NORTE ::: Recibiendo un mensaje: {}", message);
		    service.sendPositions(message);
		}
	
	@KafkaListener(
		    topics = "zona-sur-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public void zonaSur(Position message) {
		    log.info("ZONA SUR ::: Recibiendo un mensaje: {}", message);
		    service.sendPositions(message);
		}

	
	@KafkaListener(
		    topics = "zona-este-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public void zonaEste(Position message) {
		    log.info("ZONA ESTE ::: Recibiendo un mensaje: {}", message);
		    service.sendPositions(message);
		}

	
	@KafkaListener(
		    topics = "zona-oeste-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public void zonaOeste(Position message) {
		    log.info("ZONA OESTE ::: Recibiendo un mensaje: {}", message);
		    service.sendPositions(message);
		}
	
	
	@KafkaListener(
			groupId = "group-1", 
			topics = "zona-industrial-positions", 
			containerFactory = "validMessageContainerFactory")
	public void zonaIndustrial(Position message) {
		log.info("ZONA INDUSTRIAL ::: Recibiendo un mensaje: {}", message);
	    service.sendPositions(message);
	}
	
	@KafkaListener(
			groupId = "group-1", 
			topics = "centro-historico-positions", 
			containerFactory = "validMessageContainerFactory")
	public void centroHistorico(Position message) {
		log.info("CENTRO HISTORICO ::: Recibiendo un mensaje: {}", message);
	    service.sendPositions(message);
	}

}
