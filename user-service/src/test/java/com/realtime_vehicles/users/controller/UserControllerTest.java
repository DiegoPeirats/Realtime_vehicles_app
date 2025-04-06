package com.realtime_vehicles.users.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.realtime_vehicles.users.application.response.UserDTO;
import com.realtime_vehicles.users.application.service.UserServiceImpl;
import com.realtime_vehicles.users.domain.entity.User;
import com.realtime_vehicles.users.infrastructure.controller.UserController;

@WebMvcTest(UserController.class)
public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl service;
    
    @Test
    void shouldReturnUserDTOById() throws Exception {
        UserDTO userDTO = new UserDTO(1L, "diego", "diego@gmail.com");
        Mockito.when(service.getById(1L)).thenReturn(ResponseEntity.ok(userDTO));
        
        mockMvc.perform(get("/api/v1/users/1")
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("diego"))
            .andExpect(jsonPath("$.email").value("diego@gmail.com"));
    }
    
    @Test
    void shouldReturnUserDTOByRegister() throws Exception {
        UserDTO userDTO = new UserDTO(1L, "diego", "diego@gmail.com");

        Mockito.when(service.registerUser(Mockito.any(User.class)))
        .thenReturn(ResponseEntity.status(HttpStatus.CREATED).body((Object) userDTO));

        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name": "diego",
                        "email": "diego@gmail.com",
                        "password": "1234"
                    }
                """))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.email").value("diego@gmail.com"));
    }

}
