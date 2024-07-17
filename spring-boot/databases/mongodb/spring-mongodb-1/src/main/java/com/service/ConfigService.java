package com.service;

import com.mongodb.client.*;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;

    private static final String DATABASE_NAME = "mongodb_sample";
    private static final String COLLECTION_NAME = "books";

    public void listDatabases() {
        // ListDatabasesIterable<Document> databases = mongoClient.listDatabases();
        Iterable<String> databases = mongoClient.listDatabaseNames();
        databases.forEach(System.out::println);
    }

    public void getCollection() {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_NAME);
        System.out.println(collection.getNamespace());
    }

    public void getDatabase() {
        // Get database
        MongoDatabase database = mongoClient.getDatabase(DATABASE_NAME);
        // From database we can get collection
        MongoCollection<Document> collection = database.getCollection(COLLECTION_NAME);
        System.out.println(collection.getNamespace());
    }

}
