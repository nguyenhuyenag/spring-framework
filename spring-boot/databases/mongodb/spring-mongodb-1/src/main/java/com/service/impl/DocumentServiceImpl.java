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

    private final MongoTemplate mongoTemplate;
    private final TemplateService templateService;

    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Document mongoDate() {
        // Document doc = mongoTemplate.executeCommand(new Document("$eval", "new Date()"));
        // Date current = (Date) doc.get("retval");

        Document doc = mongoTemplate.executeCommand(new Document("serverStatus", 1));
        Date localTime = doc.containsKey("localTime")
                ? doc.getDate("localTime") : new Date();
        return new Document("localTime", df.format(localTime));
    }

    @Override
    public Document insertAny(String json) {
        Document doc = new Document();
        JSONObject jsonObject = new JSONObject(json);
        if (templateService.isExists(jsonObject.getString("word"))) {
            return null;
        }
        doc.append("word", jsonObject.get("word"));
        doc.append("createBy", jsonObject.get("createBy"));
        doc.append("pronounce", jsonObject.get("pronounce"));
        doc.append("translate", jsonObject.get("translate"));
        doc.append("createDate", new Date());
        return mongoTemplate.insert(doc, "new_word");
    }

    @Override
    public List<Vocabulary> basicQuery() {
        BasicQuery query = new BasicQuery("{ count : { $gt : 95 } }");
        return mongoTemplate.find(query, Vocabulary.class);
    }

    @Override
    public List<Document> bsonFilter() {
        List<Document> list = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create();) {
            MongoDatabase database = mongoClient.getDatabase("english");
            MongoCollection<Document> collection = database.getCollection("vocabulary");

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
            Bson filter = Filters.and(gt("i", 10), lt("i", 15));
            FindIterable<Document> cursor = collection.find(filter);
            // cursor.forEach(list::add);
        }
        return list;
    }

    @Override
    public List<Document> bsonSort() {
        List<Document> list = new ArrayList<>();
        try (MongoClient mongoClient = MongoClients.create();) {
            MongoDatabase database = mongoClient.getDatabase("english");
            MongoCollection<Document> collection = database.getCollection("vocabulary");
            Bson filter = Sorts.ascending("word");
            FindIterable<Document> cursor = collection.find(filter);
            // cursor.forEach(list::add);
        }
        return list;
    }

}
