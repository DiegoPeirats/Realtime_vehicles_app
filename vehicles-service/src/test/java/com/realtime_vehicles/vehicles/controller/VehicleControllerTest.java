package com.realtime_vehicles.vehicles.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realtime_vehicles.vehicles.application.response.VehicleDTO;
import com.realtime_vehicles.vehicles.application.service.VehicleServiceImpl;
import com.realtime_vehicles.vehicles.infrastructure.controller.VehicleController;

@AutoConfigureMockMvc
@WebMvcTest(VehicleController.class)
public class VehicleControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private VehicleServiceImpl service;
	
    @LocalServerPort
    private int port;
	
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Test
    void shouldReturnVehicleById() throws Exception {

        VehicleDTO vehicleDTO = new VehicleDTO(1L, "zona-norte", "car", "Toyota");
        Mockito.when(service.getById(1L)).thenReturn(ResponseEntity.ok(vehicleDTO));
        
        String url = "http://localhost:" + port + "/vehicles-app/api/v1/vehicles/1";
        
        mockMvc.perform(get(url)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.brand").value("Toyota"));
    }
    
    @Test
    void shouldReturnVehiclesByZone() throws Exception {

        List<VehicleDTO> vehicles = List.of(
        		new VehicleDTO(1L, "zona-norte", "car", "Toyota"),
        		new VehicleDTO(2L, "zona-norte", "car", "Honda"),
        		new VehicleDTO(3L, "zona-norte", "car", "Toyota"));
        
        Mockito.when(service.getByZone("zona-norte")).thenReturn(ResponseEntity.ok(vehicles));
        
        String url = "http://localhost:" + port + "/vehicles-app/api/v1/vehicles/zona-norte";
        
        mockMvc.perform(get(url)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].brand").value("Toyota"))
                .andExpect(jsonPath("$[1].brand").value("Honda"));
    }
    
    @Test
    void shouldReturnAllVehicles() throws Exception {

        List<VehicleDTO> vehicles = List.of(
        		new VehicleDTO(1L, "zona-norte", "car", "Toyota"),
        		new VehicleDTO(2L, "zona-norte", "car", "Honda"),
        		new VehicleDTO(3L, "zona-norte", "car", "Toyota"));
        
        Mockito.when(service.getAll()).thenReturn(ResponseEntity.ok(vehicles));
        
        String url = "http://localhost:" + port + "/vehicles-app/api/v1/vehicles";
        
        mockMvc.perform(get(url)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(jsonPath("$.size()").value(3))
                .andExpect(jsonPath("$[0].brand").value("Toyota"))
                .andExpect(jsonPath("$[1].brand").value("Honda"));
    }

}
