package io.reflectoring.springwebflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.reflectoring.springwebflux.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
