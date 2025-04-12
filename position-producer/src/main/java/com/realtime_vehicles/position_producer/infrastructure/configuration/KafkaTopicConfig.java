package com.realtime_vehicles.position_producer.infrastructure.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
    @Value("${spring.kafka.topic.norte}")
    private String zonaNorte;

    @Value("${spring.kafka.topic.sur}")
    private String zonaSur;
    
    @Value("${spring.kafka.topic.este}")
    private String zonaEste;

    @Value("${spring.kafka.topic.oeste}")
    private String zonaOeste;

    @Value("${spring.kafka.topic.centro}")
    private String zonaCentro;
    
    @Value("${spring.kafka.topic.industrial}")
    private String zonaIndustrial;

    @Value("${spring.kafka.topic.vehicles}")
    private String vehiclesTopic;

    @Bean
    public NewTopic zonaNorteTopic() {
        return TopicBuilder.name(zonaNorte)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic zonaSurTopic() {
        return TopicBuilder.name(zonaSur)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic zonaEsteTopic() {
        return TopicBuilder.name(zonaEste)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic zonaOesteTopic() {
        return TopicBuilder.name(zonaOeste)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic zonaCentroTopic() {
        return TopicBuilder.name(zonaCentro)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic zonaIndustrialTopic() {
        return TopicBuilder.name(zonaIndustrial)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic vehiclesTopicTopic() {
        return TopicBuilder.name(vehiclesTopic)
                .partitions(1)
                .replicas(1)
                .build();
    }
}