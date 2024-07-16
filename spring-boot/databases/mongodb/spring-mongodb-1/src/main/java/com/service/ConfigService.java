package com.service;

import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoIterable;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConfigService {

    private final MongoClient mongoClient;

    public void listDatabases() {
        // ListDatabasesIterable<Document> databases = mongoClient.listDatabases();
        Iterable<String> databases = mongoClient.listDatabaseNames();
        databases.forEach(System.out::println);
    }

}
