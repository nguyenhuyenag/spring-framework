package com.service.impl;

import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.lt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.service.DocumentService;
import com.service.TemplateService;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    // private final MongoClient mongoClient;
    private final MongoTemplate mongoTemplate;
    private final TemplateService templateService;

    private static final String COLLECTION_NAME = "books";

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Document mongoDate() {
        // MongoDB db.eval() command has been in deprecation mode since MongoDB v3.0.
        // Document doc = mongoTemplate.executeCommand(new Document("$eval", "new Date()"));
        // Date current = (Date) doc.get("retval");

        Document doc = mongoTemplate.executeCommand(new Document("serverStatus", 1));
        Date localTime = doc.containsKey("localTime")
                ? doc.getDate("localTime") : new Date();
        return new Document("localTime", df.format(localTime));
    }

    @Override
    public List<Document> bsonSort() {
        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_NAME);
        Bson filter = Filters.and(Filters.gte("pageCount", 700));
        Bson sortCriteria = Sorts.orderBy(Sorts.descending("title"), Sorts.ascending("author"));
        FindIterable<Document> cursor = collection.find(filter)
                .sort(sortCriteria);
        // List<Document> result = new ArrayList<>();
        // cursor.forEach(result::add);
        return cursor.into(new ArrayList<>());
    }

    @Override
    public Document insert(Document document) {
        // JSONObject jsonObject = new JSONObject(document);
        document.put("isbn", System.currentTimeMillis());
        return mongoTemplate.insert(document, COLLECTION_NAME);
    }

    @Override
    public List<Vocabulary> basicQuery() {
        BasicQuery query = new BasicQuery("{ count : { $gt : 95 } }");
        return mongoTemplate.find(query, Vocabulary.class);
    }

    @Override
    public List<Document> bsonFilter() {
        // try (MongoClient mongoClient = MongoClients.create()) {

        // MongoDatabase database = mongoClient.getDatabase("english");
        // MongoCollection<Document> collection = database.getCollection("vocabulary");

        MongoCollection<Document> collection = mongoTemplate.getCollection(COLLECTION_NAME);

        /**
         * Comparison
         */
        // Bson filter = Filters.eq("word", "able");
        // Bson filter = Filters.ne("count", 5);
        // Bson filter = Filters.in("count", 90, 97);
        // Bson filter = Filters.in("count", Arrays.asList(90, 97));

        // tương tự cho `Filters.nin()`
        // Bson filter = Filters.gt("count", 97);
        // Bson filter = Filters.gte("count", 5);
        // Bson filter = Filters.lt("count", 5);
        // Bson filter = Filters.lte("count", 5);

        /**
         * Logical
         */
        Bson filter = Filters.and(Filters.gt("_id", 10), Filters.lt("_id", 15));
        FindIterable<Document> cursor = collection.find(filter);

        List<Document> list = new ArrayList<>();
        cursor.forEach(list::add);

        return list;
    }

}
