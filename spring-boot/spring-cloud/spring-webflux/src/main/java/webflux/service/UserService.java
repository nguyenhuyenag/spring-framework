package webflux.service;

import java.util.Collections;

import org.bson.Document;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.model.User;
import webflux.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

	private final ReactiveMongoTemplate reactiveMongoTemplate;
	private final UserRepository userRepository;

	public Mono<User> createUser(User user) {
		return userRepository.save(user);
	}

	public Flux<?> getAllUsers() {
		return reactiveMongoTemplate.findAll(Document.class, "user");
		// return userRepository.findAll();
	}

	public Mono<User> findById(String userId) {
		return userRepository.findById(userId);
	}

	public Mono<User> updateUser(String userId, User user) {
		return userRepository.findById(userId).flatMap(dbUser -> {
			dbUser.setAge(user.getAge());
			dbUser.setSalary(user.getSalary());
			return userRepository.save(dbUser);
		});
	}

	public Mono<User> deleteUser(String userId) {
		return userRepository.findById(userId)
				.flatMap(existingUser -> userRepository.delete(existingUser).then(Mono.just(existingUser)));
	}

	public Flux<User> fetchUsers(String name) {
		Query query = new Query().with(Sort.by(Collections.singletonList(Sort.Order.asc("age"))));
		query.addCriteria(Criteria.where("name").regex(name));

		return reactiveMongoTemplate.find(query, User.class);
	}
}
