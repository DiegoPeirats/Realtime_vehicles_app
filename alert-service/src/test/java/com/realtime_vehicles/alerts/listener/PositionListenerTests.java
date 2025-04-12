package com.realtime_vehicles.alerts.listener;

import java.time.Instant;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.realtime_vehicles.alerts.application.service.AlertServiceImpl;
import com.realtime_vehicles.alerts.infrastructure.listener.PositionConsumerListener;
import com.realtime_vehicles.alerts.infrastructure.response.Position;

@ExtendWith(MockitoExtension.class)
public class PositionListenerTests {
	
    @InjectMocks
    private PositionConsumerListener listener;

    @Mock
    private AlertServiceImpl alertService;

    @Test
    void shouldCallSendAlert_WhenMessageReceived() {

        Position mockPosition = new Position();
        mockPosition.setX(10.0);
        mockPosition.setY(20.0);
        mockPosition.setVehicleCode("1L");
        mockPosition.setTimestamp(Instant.now());
        mockPosition.setZoneCode("zona-norte");

        listener.consumePosition(mockPosition);

        Mockito.verify(alertService).sendAlert(mockPosition);
    }

}
