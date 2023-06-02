package com.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.entity.Restaurant;

public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {

}
