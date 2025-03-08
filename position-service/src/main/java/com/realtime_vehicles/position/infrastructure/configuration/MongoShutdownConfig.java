package com.realtime_vehicles.position.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import com.mongodb.client.MongoClient;

@Configuration
public class MongoShutdownConfig {

    @Autowired
    private MongoClient mongoClient;

    @EventListener(ContextClosedEvent.class)
    public void onShutdown() {
        mongoClient.close();
    }
}
