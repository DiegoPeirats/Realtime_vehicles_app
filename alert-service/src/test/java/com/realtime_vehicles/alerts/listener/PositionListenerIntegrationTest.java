package com.realtime_vehicles.alerts.listener;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import com.realtime_vehicles.alerts.application.service.AlertServiceImpl;
import com.realtime_vehicles.alerts.infrastructure.listener.PositionConsumerListener;
import com.realtime_vehicles.alerts.infrastructure.response.Position;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, topics = {"zona-norte-positions"}, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class PositionListenerIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Position> kafkaTemplate;

    @SpyBean
    private PositionConsumerListener listener;

    @SpyBean
    private AlertServiceImpl alertService;

    private final CountDownLatch latch = new CountDownLatch(1);

    @Test
    void shouldConsumeMessageFromKafka() throws InterruptedException {
        Position position = new Position("1L", "1L", 10.0, 20.0, "zona-norte", Instant.now());

        // Simular que se cuenta la llamada del listener
        Mockito.doAnswer(invocation -> {
            latch.countDown();
            return invocation.callRealMethod();
        }).when(listener).consumePosition(Mockito.any(Position.class));

        kafkaTemplate.send("zona-norte-positions", position);

        // Esperamos hasta que se consuma
        boolean messageConsumed = latch.await(5, TimeUnit.SECONDS);
        assertTrue(messageConsumed, "El mensaje no fue consumido en el tiempo esperado");

        // Verificamos
        Mockito.verify(listener).consumePosition(Mockito.any(Position.class));
        Mockito.verify(alertService).sendAlert(Mockito.any(Position.class));
    }
}

