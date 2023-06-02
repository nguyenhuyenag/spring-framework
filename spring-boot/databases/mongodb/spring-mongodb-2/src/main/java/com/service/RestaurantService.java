package com.service;

import java.util.List;

import com.entity.Restaurant;

public interface RestaurantService {

	void findIn(); // in

	Restaurant findByRestaurantId(String id);

	List<Restaurant> findByRegex();

	List<Restaurant> findLessThanAndGreatThan();

	List<Restaurant> sort();

	List<Restaurant> page(int from, int to);

}
