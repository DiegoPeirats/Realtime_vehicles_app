package com.realtime_vehicles.historial.infrastructure.configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.realtime_vehicles.historial.infrastructure.listener.PositionReceived;
import com.realtime_vehicles.historial.domain.entity.PositionSaved;

@Configuration
public class HistorialConfiguration {
	
	@Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.addMappings(new PropertyMap<PositionReceived, PositionSaved>() {
            @Override
            protected void configure() {
                skip(destination.getId()); 
            }
        });

        return modelMapper;
    }

}
