package webflux.controller;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import webflux.model.User;
import webflux.service.UserService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<User> create(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping
	public Flux<?> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public Mono<ResponseEntity<User>> getUserById(@PathVariable String userId) {
		Mono<User> user = userService.findById(userId);
		return user.map(ResponseEntity::ok).defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@PutMapping("/{userId}")
	public Mono<ResponseEntity<User>> updateUserById(@PathVariable String userId, @RequestBody User user) {
		return userService.updateUser(userId, user).map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{userId}")
	public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String userId) {
		return userService.deleteUser(userId).map(r -> ResponseEntity.ok().<Void>build())
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@GetMapping("/search")
	public Flux<User> searchUsers(@RequestParam("name") String name) {
		return userService.fetchUsers(name);
	}

	@GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<?> streamAllUsers() {
		return userService.getAllUsers()
				.flatMap(user -> Flux
						.zip(Flux.interval(Duration.ofSeconds(2)), Flux.fromStream(Stream.generate(() -> user)))
						.map(Tuple2::getT2));
	}
}
