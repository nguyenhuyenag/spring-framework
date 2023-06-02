package com.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.entity.Restaurant;

public interface RestaurantRepotitory extends MongoRepository<Restaurant, String> {

	Restaurant findByName(String name);

	List<Restaurant> findByRestaurantIdGreaterThan(String id); // LessThan

	List<Restaurant> findByNameEndingWith(String regexp);

	List<Restaurant> findByNameStartingWith(String regexp);

	List<Restaurant> findByRestaurantIdBetween(String from, String to);

	List<Restaurant> findByNameLikeOrderByRestaurantIdDesc(String namelike);

	// JSON query methods
	@Query("{ 'name' : ?0 }")
	List<Restaurant> findByNameUsingJSON(String name);

	@Query("{ 'name' : { $regex: ?0 } }")
	List<Restaurant> findRestaurantByRegexpName(String regexp); // "^A"

	@Query("{ 'restaurantId' : { $gt: ?0, $lt: ?1 } }")
	List<Restaurant> findByRestaurantIdBetween(int from, int to);

}
