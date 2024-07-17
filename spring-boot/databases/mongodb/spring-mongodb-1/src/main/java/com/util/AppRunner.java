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
    private final ConfigService configService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        configService.getDatabase();
    }

}
