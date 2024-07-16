package com.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@RequiredArgsConstructor
public class MongoConfig extends AbstractMongoClientConfiguration {

    private final MongoProperties mongoProperties;

    @Override
    public MongoClient mongoClient() {
        MongoClientSettings settings = MongoClientSettings.builder() //
                .applyConnectionString(new ConnectionString(mongoProperties.getUri())) //
                .build();
        return MongoClients.create(settings);
    }

    @Override
    protected String getDatabaseName() {
        // Cần phải khai báo: spring.data.mongodb.database=db_name
        return mongoProperties.getDatabase();
    }

}
