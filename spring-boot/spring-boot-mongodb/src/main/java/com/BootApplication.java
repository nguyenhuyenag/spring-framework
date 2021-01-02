package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.repository.RestaurantRepotitory;
import com.template.RestaurantService;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
	
	@Autowired
	RestaurantService service;

	@Autowired
	RestaurantRepotitory repository;

	@Override
	public void run(String... args) throws Exception {
		// repository.findAll().stream().limit(3).forEach(t -> System.out.println(t.getAddress()));
		System.out.println(service.findByRestaurantId("30075445").toString());
	}

}
