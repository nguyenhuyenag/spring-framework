package io.webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import io.webflux.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
