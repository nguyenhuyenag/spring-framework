package webflux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import webflux.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
