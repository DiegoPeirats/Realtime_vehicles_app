package com.realtime_vehicles.monitoring.listener;

import java.time.Instant;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;

import com.realtime_vehicles.monitoring.application.service.MonitoringServiceImpl;
import com.realtime_vehicles.monitoring.infrastructure.listener.PositionConsumerListener;
import com.realtime_vehicles.monitoring.infrastructure.response.Position;


@SpringBootTest
@EmbeddedKafka(partitions = 1, topics = { "zona-norte-positions" })
public class KafkaListenerTests {

    @MockBean
    private KafkaTemplate<String, Position> kafkaTemplate;

    @Autowired
    private PositionConsumerListener listener;

    @MockBean
    private MonitoringServiceImpl service;

    @Test
    public void testReceivingMessage() {
        Position position = new Position("1L", 10.0, 20.0, "Zona Norte", Instant.now());

        kafkaTemplate.send("zona-norte-positions", position);

        listener.zonaNorte(position);

        Mockito.verify(service).sendPositions(position);
    }
}