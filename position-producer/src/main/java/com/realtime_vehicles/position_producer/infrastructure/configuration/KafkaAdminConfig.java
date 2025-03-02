package com.realtime_vehicles.position_producer.infrastructure.configuration;

import java.util.HashMap;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaAdminConfig {
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Bean
	KafkaAdmin kafkaAdmin() {
		var configs = new HashMap<String, Object>();
		configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		return new KafkaAdmin(configs);
	}
	
	@Bean
	KafkaAdmin.NewTopics topics(){
		return new KafkaAdmin.NewTopics(
				TopicBuilder.name("zona-norte-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("zona-sur-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("zona-este-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("zona-oeste-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("centro-historico-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("zona-industrial-positions").partitions(2).replicas(1).build(),
				TopicBuilder.name("vehicle-position").partitions(2).replicas(1).build()
				);
	}

}
