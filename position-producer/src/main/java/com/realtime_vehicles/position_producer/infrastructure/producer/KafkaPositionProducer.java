package com.realtime_vehicles.position_producer.infrastructure.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import com.realtime_vehicles.position_producer.application.PositionServiceImpl;
import com.realtime_vehicles.position_producer.domain.Position;

import jakarta.annotation.PostConstruct;

@Service
public class KafkaPositionProducer {
	
	@Autowired
	private KafkaTemplate<String,Position> kafkaTemplate;
	
	@Autowired
	private PositionServiceImpl service;
	
	private static final Logger log = LoggerFactory.getLogger(KafkaPositionProducer.class);
	
	public String getTopic(String zone) {
		return zone.toLowerCase().replace(" ", "-") + "-positions";
	}
	
	public void sendPositionToKafka(String topic, Position position) {
		
		Message<Position> message = MessageBuilder
				.withPayload(position)
				.setHeader(KafkaHeaders.TOPIC, topic)
				.build();
		
        kafkaTemplate.send(message)
            .whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Error al enviar el mensaje a Kafka: {}", ex.getMessage());
                    return;
                }
                log.info("Mensaje enviado al topic '{}': {}", topic, result.getProducerRecord().value());
                log.info("Partition: {}, Offset: {}", result.getRecordMetadata().partition(), 
                        result.getRecordMetadata().offset());
            });
    }

    @PostConstruct
    public void streamLatestVehiclePositionsToKafka() {
        service.getVehiclesPositions()
            .doOnNext(position -> sendPositionToKafka("vehicle-positions", position))
            .doOnError(ex -> log.error("Error en el flujo de últimas posiciones: {}", ex.getMessage()))
            .subscribe();
    }

    @PostConstruct
    public void streamZonePositionsToKafka() {
        service.getZonesPositions()
            .doOnNext(position -> sendPositionToKafka(getTopic(position.getZoneCode()), position))
            .doOnError(ex -> log.error("Error en el flujo de posiciones de zona: {}", ex.getMessage()))
            .subscribe();
    }

}
