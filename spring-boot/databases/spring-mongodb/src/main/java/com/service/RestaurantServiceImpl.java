package com.service;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.entity.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private MongoTemplate mongoTemplate;

	Consumer<Restaurant> show = System.out::println;

	@Override
	public Restaurant findByRestaurantId(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("restaurantId").is(id));
		List<Restaurant> list = mongoTemplate.find(query, Restaurant.class);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Restaurant> findByRegex() {
		Query query = new Query();
		// query.addCriteria(Criteria.where("name").regex("^N")); // bat dau bang chu N
		query.addCriteria(Criteria.where("name").regex("c$")); // ket thuc bang chu c
		return mongoTemplate.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> findLessThanAndGreatThan() {
		Query query = new Query();
		// gt("40361521").lt("40361921")); >, <
		query.addCriteria(Criteria.where("restaurant_id").gte("40361521").lte("40361921")); // >=, <=
		return mongoTemplate.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> sort() {
		Query query = new Query();
		query.with(Sort.by(Sort.Direction.ASC, "name"));
		return mongoTemplate.find(query, Restaurant.class);
	}

	@Override
	public List<Restaurant> page(int from, int to) {
		final Pageable pageableRequest = PageRequest.of(from, to);
		Query query = new Query();
		query.with(pageableRequest);
		return mongoTemplate.find(query, Restaurant.class);
	}

	@Override
	public void findIn() {
		Query query = new Query();
		query.addCriteria(Criteria.where("restaurant_id").in(Arrays.asList("30112340", "30075445")));
		List<Restaurant> list = mongoTemplate.find(query, Restaurant.class);
		list.forEach(show);
	}

}
