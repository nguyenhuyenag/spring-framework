//package com.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.mongo.MongoProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//	@Autowired
//	private MongoProperties mongoProperties;
//
//	@Override
//	protected String getDatabaseName() {
//		return mongoProperties.getDatabase();
//	}
//
//	@Override
//	public MongoClient mongoClient() {
//		return MongoClients.create(mongoProperties.getUri());
//	}
//}
