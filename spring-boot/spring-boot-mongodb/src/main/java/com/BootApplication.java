package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.RestaurantRepotitory;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
	@Autowired
	RestaurantRepotitory repository;

	@Override
	public void run(String... args) throws Exception {
		repository.findAll().stream().limit(5).forEach(t -> System.out.println(t));
	}
	
//	@Bean
//    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
//        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
//        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
//        return mongoTemplate;
//    }

}
