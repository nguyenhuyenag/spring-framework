package com;

import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.entity.Restaurant;
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
		
		Consumer<Restaurant> show = System.out::println;
		
		// System.out.println(service.findByRegex().size());
		// System.out.println(service.findByRestaurantId("30075445").toString());
		// service.findByRegex().forEach(t -> System.out.println(t));
		// service.findLessThanAndGreatThan().forEach(t -> System.out.println(t));
		// service.page(1, 2).forEach(print);
		// service.page(3, 4).forEach(print);
		// service.findIn();

		// spring mongo
		repository.findByNameEndingWith("C").forEach(show);
		// repository.findByNameStartingWith("C").forEach(show);
		// repository.findByRestaurantIdBetween("40361521", "40361921").forEach(show);
		// repository.findByNameLikeOrderByRestaurantIdDesc("A");
		// repository.findByNameUsingJSON("Morris Park Bake Shop").forEach(show);
		// repository.findRestaurantByRegexpName("^A").forEach(show);
		// repository.findRestaurantByRegexpName("c$");
		// repository.findByRestaurantIdBetween("40361521", "40361921").forEach(show);
		// System.out.println(repository.findByName("Morris Park Bake Shop"));
		// System.out.println(repository.findByRestaurantIdGreaterThan("40361921").size());
	}

}
