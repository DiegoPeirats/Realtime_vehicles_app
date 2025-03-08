package com.realtime_vehicles.position.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;


@Configuration
public class MongoConfig {

    @Bean
    MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017/vehicles_app");
    }
    
    @Bean
    ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient, MongoDatabaseFactory mongoDatabaseFactory, MongoMappingContext context) {
        return new ReactiveMongoTemplate(mongoClient, "vehicles_app");
    }
}