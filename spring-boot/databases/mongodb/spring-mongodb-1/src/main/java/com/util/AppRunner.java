package com.util;

import com.mongodb.client.model.Filters;
import com.service.ConfigService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Bson filter = Filters.and(Filters.gt("qty", 3), Filters.lt("qty", 9));
        // Retrieves documents that match the filter and prints them as JSON
        // mongoTemplate.find(filter).forEach(doc -> System.out.println(doc.toJson()));
    }

}
