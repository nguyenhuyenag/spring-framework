package com.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.entity.Restaurant;

public interface RestaurantRepotitory extends MongoRepository<Restaurant, String> {

}
