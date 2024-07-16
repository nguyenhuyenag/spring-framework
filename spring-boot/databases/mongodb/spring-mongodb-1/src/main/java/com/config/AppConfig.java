package com.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

/*
    https://docs.spring.io/spring-data/mongodb/reference/mongodb/configuration.html
 */
@Configuration
public class AppConfig {

    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create();
    }

    @Bean
    public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
