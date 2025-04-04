package com.realtime_vehicles.monitoring.infrastructure.configuration;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.realtime_vehicles.monitoring.infrastructure.response.Position;


@Configuration
public class PositionConsumerConfiguration {
	
	@Autowired
	private KafkaProperties kafkaProperties;

	private static final Logger log = LoggerFactory.getLogger(PositionConsumerConfiguration.class);
	
	@Bean
	ConsumerFactory<String, Position> consumerFactory(){
		var configs = new HashMap<String, Object>();
		
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		JsonDeserializer<Position> deserializer = new JsonDeserializer<>(Position.class);
        deserializer.addTrustedPackages("*");

        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
	}
	
	@Bean
	ConcurrentKafkaListenerContainerFactory<String, Position> validMessageContainerFactory
	(ConsumerFactory<String, Position> consumerFactory){
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Position>();
		factory.setConsumerFactory(consumerFactory);
		factory.setRecordInterceptor(validMessage());
		return factory;
	}
	
	@Bean
	public RecordInterceptor<String, Position> validMessage(){
		return (record,consumer) -> {
			log.info("Intercepted record: {}", record);
			return record;
		};
	}

}
