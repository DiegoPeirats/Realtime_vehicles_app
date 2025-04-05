package com.realtime_vehicles.vehicles.service;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.realtime_vehicles.vehicles.application.response.VehicleDTO;
import com.realtime_vehicles.vehicles.application.service.VehicleServiceImpl;
import com.realtime_vehicles.vehicles.domain.entity.Vehicle;
import com.realtime_vehicles.vehicles.infrastructure.repository.VehicleRepository;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehiclesServiceTests {
	
	@Mock
    private VehicleRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private VehicleServiceImpl service;
    
    private Vehicle vehicle;
    private VehicleDTO vehicleDTO;
    
    @BeforeEach
    void setUp() {
    	vehicle = new Vehicle(1L, "zona-norte", "sedan", "toyota");
    	vehicleDTO = new VehicleDTO(1L, "zona-norte", "sedan", "toyota");
    	
    	BDDMockito.lenient().when(modelMapper.map(vehicle, VehicleDTO.class)).thenReturn(vehicleDTO);
    }
    
    @Test
    @Order(1)
    void ShouldReturnVehicleDTOSearchingById() {
    	
    	BDDMockito.given(repository.findById(1L)).willReturn(Optional.of(vehicle));
    	
    	ResponseEntity<VehicleDTO> response = service.getById(1L);
    	
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assertions.assertEquals(vehicleDTO.getId(), response.getBody().getId());
    	Assertions.assertNotNull(response.getBody());
    }
    
    @Test
    @Order(2)
    void ShouldReturnVehicleListSearchigByZone() {
    	BDDMockito.given(repository.findByZoneCode("zona-norte")).willReturn(List.of(vehicle));
    	
    	ResponseEntity<List<VehicleDTO>> response = service.getByZone("zona-norte");
    	
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assertions.assertEquals(response.getBody().get(0).getBrand(), "toyota");
    	Assertions.assertEquals(response.getBody().size(), 1);
    }
    
    @Test
    @Order(3)
    void ShouldReturnVehicleListSearchigAll() {
    	BDDMockito.given(repository.findAll()).willReturn(List.of(vehicle));
    	
    	ResponseEntity<List<VehicleDTO>> response = service.getAll();
    	
    	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    	Assertions.assertEquals(response.getBody().get(0).getBrand(), "toyota");
    	Assertions.assertEquals(response.getBody().size(), 1);
    }
}
