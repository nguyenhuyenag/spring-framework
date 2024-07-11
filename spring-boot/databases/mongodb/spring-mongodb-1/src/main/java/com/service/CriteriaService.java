package com.service;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CriteriaService {

    private final MongoTemplate mongoTemplate;

    private static final String COLLECTION_NAME = "books";

    public void test() {
        // mongoTemplate.remove()
    }

    public List<Document> findLte() {
        Query query = new Query();
        // Tìm tất cả các id <= 20
        query.addCriteria(Criteria.where("_id").lte(20).gte(10));
        query.fields().exclude("shortDescription", "longDescription");
        return mongoTemplate.find(query, Document.class, COLLECTION_NAME);
    }

    public List<Document> findRegex() {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").regex("^A"));
        query.fields().exclude("shortDescription", "longDescription");
        return mongoTemplate.find(query, Document.class, COLLECTION_NAME);
    }

}
