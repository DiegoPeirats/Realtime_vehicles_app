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
		public Position zonaNorte(Position message) {
		    log.info("ZONA NORTE ::: Recibiendo un mensaje: {}", message);
		    service.sendAlert(message);
		    return message;
		}
	
	@KafkaListener(
		    topics = "zona-sur-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public Position zonaSur(Position message) {
		    log.info("ZONA SUR ::: Recibiendo un mensaje: {}", message);
		    service.sendAlert(message);
		    return message;
		}

	
	@KafkaListener(
		    topics = "zona-este-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public Position zonaEste(Position message) {
		    log.info("ZONA ESTE ::: Recibiendo un mensaje: {}", message);
		    service.sendAlert(message);
		    return message;
		}

	
	@KafkaListener(
		    topics = "zona-oeste-positions",
		    groupId = "group-1",
		    containerFactory = "validMessageContainerFactory"
		)
		public Position zonaOeste(Position message) {
		    log.info("ZONA OESTE ::: Recibiendo un mensaje: {}", message);
		    service.sendAlert(message);
		    return message;
		}
	
	
	@KafkaListener(
			groupId = "group-1", 
			topics = "zona-industrial-positions", 
			containerFactory = "validMessageContainerFactory")
	public Position zonaIndustrial(Position message) {
		log.info("ZONA INDUSTRIAL ::: Recibiendo un mensaje: {}", message);
	    service.sendAlert(message);
	    return message;
	}
	
	@KafkaListener(
			groupId = "group-1", 
			topics = "centro-historico-positions", 
			containerFactory = "validMessageContainerFactory")
	public Position centroHistorico(Position message) {
		log.info("CENTRO HISTORICO ::: Recibiendo un mensaje: {}", message);
	    service.sendAlert(message);
	    return message;
	}
	
	@KafkaListener(
			groupId = "group-1", 
			topics = "vehiclePosition", 
			containerFactory = "validMessageContainerFactory")
	public Position vehiclePosition(Position message) {
		log.info("VEHICULO ::: Recibiendo un mensaje: {}", message);
	    service.sendAlert(message);
	    return message;
	}

}
