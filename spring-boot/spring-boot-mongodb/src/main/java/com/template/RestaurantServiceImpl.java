package com.template;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.entity.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Restaurant findByRestaurantId(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("restaurant_id").is(id));
		List<Restaurant> list = mongoTemplate.find(query, Restaurant.class);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
