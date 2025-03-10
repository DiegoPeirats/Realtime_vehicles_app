package com.realtime_vehicles.position_producer.producer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import com.realtime_vehicles.position_producer.application.PositionServiceImpl;
import com.realtime_vehicles.position_producer.domain.Position;
import com.realtime_vehicles.position_producer.infrastructure.client.vehicles.VehiclesClient;
import com.realtime_vehicles.position_producer.infrastructure.client.zones.ZoneClient;
import com.realtime_vehicles.position_producer.infrastructure.producer.KafkaPositionProducer;

import reactor.core.publisher.Flux;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class KafkaProducerTests {
	
	@MockBean
    private ZoneClient zoneClient;

    @MockBean
    private VehiclesClient vehiclesClient;

    @Mock
    private KafkaTemplate<String, Position> kafkaTemplate;

    @Mock
    private PositionServiceImpl service;
	
	@InjectMocks
    private KafkaPositionProducer kafkaProducer;

    @Captor
    private ArgumentCaptor<String> topicCaptor; // capturar los argumentos que se pasan al método send() de KafkaTemplate

    @Captor
    private ArgumentCaptor<Position> positionCaptor; // capturar los argumentos que se pasan al método send() de KafkaTemplate

    private Position samplePosition;
    
    private Flux<Position> positionFlux;

    @BeforeAll
    void setup() {
        samplePosition = new Position("1L", 10.0, 20.0, "ZonaA", Instant.now());
        positionFlux = Flux.just(samplePosition);
    }
    
    @Test
    @Order(1)
    void testGetTopic() {
        String topic = kafkaProducer.getTopic("Zona Central");
        assertEquals("zona-central-positions", topic);
    }
    
    @Test
    @Order(2)
    void testSendPositionToKafka() {
        String topic = "test-topic";
        CompletableFuture<SendResult<String, Position>> future = CompletableFuture.completedFuture(mock(SendResult.class));

        when(kafkaTemplate.send(anyString(), anyString(), any(Position.class)))
                .thenReturn(future);

        kafkaProducer.sendPositionToKafka(topic, samplePosition);

        verify(kafkaTemplate).send(topicCaptor.capture(), eq(samplePosition.getVehicleCode().toString()), positionCaptor.capture());

        assertEquals(topic, topicCaptor.getValue());
        assertEquals(samplePosition, positionCaptor.getValue());
    }
    
    @Test
    @Order(3)
    void teststreamLatestVehiclePositionsToKafka() {
    	when(service.getVehiclesPositions()).thenReturn(positionFlux);
    	
    	kafkaProducer.streamLatestVehiclePositionsToKafka();
    	
    	verify(kafkaTemplate).send(eq("latest-vehicle-positions"), anyString(), eq(samplePosition));
    }
    
    @Test
    @Order(4)
    void testStreamZonePositionsToKafka() {

    	when(service.getZonesPositions()).thenReturn(positionFlux);
    	
    	kafkaProducer.streamZonePositionsToKafka();

        String expectedTopic = "zonaa-positions";
    	
    	verify(kafkaTemplate).send(eq(expectedTopic), anyString(), eq(samplePosition));
    	
    }

}
