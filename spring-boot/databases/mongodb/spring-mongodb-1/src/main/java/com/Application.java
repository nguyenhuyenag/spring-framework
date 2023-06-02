package com;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.RestaurantRepository;

// @EnableMongoAuditing
// @EnableMongoRepositories
@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	RestaurantRepository repository;

//	@Autowired
//	MongodbTransaction mongodbTransaction;

	public void find() {
		List<?> findAll = repository.findAll();
		if (!findAll.isEmpty()) {
			findAll.forEach(t -> System.out.println(t.toString()));
		}
	}

	@Override
	public void run(String... args) throws Exception {
		// find();
		// insert();
		// mongodbTransaction.testTransaction();
		// testTransaction();
		// System.out.println("OK");
	}

}
