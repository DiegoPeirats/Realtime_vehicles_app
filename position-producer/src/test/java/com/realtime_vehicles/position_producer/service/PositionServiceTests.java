package com.realtime_vehicles.position_producer.service;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.realtime_vehicles.position_producer.application.PositionServiceImpl;
import com.realtime_vehicles.position_producer.domain.Position;
import com.realtime_vehicles.position_producer.infrastructure.client.PositionClient;
import com.realtime_vehicles.position_producer.infrastructure.client.vehicles.Vehicle;
import com.realtime_vehicles.position_producer.infrastructure.client.vehicles.VehiclesClient;
import com.realtime_vehicles.position_producer.infrastructure.client.zones.Zone;
import com.realtime_vehicles.position_producer.infrastructure.client.zones.ZoneClient;
import static org.junit.jupiter.api.Assertions.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PositionServiceTests {

	@MockBean
    private ZoneClient zoneClient;

    @MockBean
    private VehiclesClient vehiclesClient;

    @Mock
    private PositionClient positionClient;

    @InjectMocks
    private PositionServiceImpl positionService;
    
    private List<Zone> mockZones;
    
    private List<Vehicle> mockVehicles;
    
    @BeforeAll
    private void setup() {
    	Zone zoneA = new Zone("ZonaA", "Zona Norte positions", 10000);
		Zone zoneB = new Zone("ZonaB", "Zona Sur positions", 10000);
        
        mockZones = List.of(zoneA, zoneB);
        
    	Vehicle vehicleA = new Vehicle(1L, "ZonaA", "moto", "honda");
    	Vehicle vehicleB = new Vehicle(2L, "ZonaB", "coche", "toyota");
        
        mockVehicles = List.of(vehicleA, vehicleB);
    	
    }

    @Test
    @Order(1)
    void testGetZonesCodes() {

        Mockito.when(zoneClient.getZones()).thenReturn(mockZones);

        List<String> result = positionService.getZonesCodes();

        assertEquals(2, result.size());
        assertTrue(result.contains("ZonaA"));
        assertTrue(result.contains("ZonaB"));
    }

    @Test
    @Order(2)
    void testGetVehiclesCodes() {

        Mockito.when(vehiclesClient.getVehicles()).thenReturn(mockVehicles);

        List<Long> result = positionService.getVehiclesCodes();

        assertEquals(2, result.size());
        assertTrue(result.contains(1L));
        assertTrue(result.contains(2L));
    }

    @Test
    @Order(3)
    void testGetZonesPositions() {

        Position positionA = new Position("1L", 10.0, 20.0, "ZonaA", Instant.now());
        Position positionB = new Position("2L", 30.0, 40.0, "ZonaB", Instant.now());

        Mockito.when(zoneClient.getZones()).thenReturn(mockZones);

        Mockito.when(positionClient.getPositionsByZone("ZonaA")).thenReturn(Flux.just(positionA));
        Mockito.when(positionClient.getPositionsByZone("ZonaB")).thenReturn(Flux.just(positionB));

        StepVerifier.create(positionService.getZonesPositions())
            .expectNext(positionA)
            .expectNext(positionB)
            .verifyComplete();
    }

    @Test
    @Order(4)
    void testGetVehiclesPositions() {

        Position positionA = new Position("1L", 10.0, 20.0, "ZoneA", Instant.now());
        Position positionB = new Position("2L", 30.0, 40.0, "ZoneB", Instant.now());

        Mockito.when(vehiclesClient.getVehicles()).thenReturn(mockVehicles);

        Mockito.when(positionClient.getVehiclePosition(1L)).thenReturn(Mono.just(positionA));
        Mockito.when(positionClient.getVehiclePosition(2L)).thenReturn(Mono.just(positionB));

        StepVerifier.create(positionService.getVehiclesPositions())
            .expectNext(positionA)
            .expectNext(positionB)
            .verifyComplete();
    }
}