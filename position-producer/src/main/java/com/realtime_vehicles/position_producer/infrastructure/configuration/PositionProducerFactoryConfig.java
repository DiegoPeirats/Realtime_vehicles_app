package com.realtime_vehicles.position_producer.infrastructure.configuration;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.realtime_vehicles.position_producer.domain.Position;


@Configuration
public class PositionProducerFactoryConfig {
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Bean
	ProducerFactory<String,Position> producerFactory(){
		var configs = new HashMap<String, Object>();
		
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		
		return new DefaultKafkaProducerFactory<>(configs);
		
	}
	
	@Bean
	KafkaTemplate<String, Position> kafkaTemplate(){
		return new KafkaTemplate<>(producerFactory());
	}
}
