package com.example.cryptocurrencyservice.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Value("${mongodb.url}")
    private String mongoUrl;

    @Value("${mongodb.database}")
    private String mongoDatabase;

    @Bean
    public MongoDatabase mongoDatabase() {
        try (MongoClient mongoClient = MongoClients.create(mongoUrl)) {
            return mongoClient.getDatabase(mongoDatabase);
        }
    }
}