package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

	@Autowired
	private MongoProperties mongoProperties;

	@Bean
	public MongoTransactionManager transactionManager(MongoDatabaseFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}

	@Override
	public MongoClient mongoClient() {
		MongoClientSettings settings = MongoClientSettings.builder() //
				.applyConnectionString(new ConnectionString(mongoProperties.getUri())) //
				// .retryReads(false) //
				// .retryWrites(false) //
				.build();
		return MongoClients.create(settings);
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getDatabase();
	}

}
