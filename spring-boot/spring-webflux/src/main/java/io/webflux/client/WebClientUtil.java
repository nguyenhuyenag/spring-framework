package io.webflux.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// @Slf4j
@Component
@RequiredArgsConstructor
public class WebClientUtil {

	private final WebClient webClient;

	public WebClient.ResponseSpec getFakeUsers() {
		return webClient.get().uri("https://randomuser.me/api/").retrieve();
	}

	private static void fakeUser() {
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "https://randomuser.me/api/";
		// Fetch JSON response as String wrapped in ResponseEntity
		ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
		String productsJson = response.getBody();
		System.out.println(productsJson);
	}

//	public Mono<User> postUser(User user) {
//		return webClient.post().uri("http://localhost:9000/api/users").header("Authorization", "Basic MY_PASSWORD")
//				.accept(MediaType.APPLICATION_JSON).body(Mono.just(user), User.class).retrieve().bodyToMono(User.class)
//				.log().retryWhen(Retry.backoff(10, Duration.ofSeconds(2))).onErrorReturn(new User())
//				.doOnError(throwable -> log.error("Result error out for POST user", throwable))
//				.doFinally(signalType -> log.info("Result Completed for POST User: {}", signalType));
//	}

	public static void main(String[] args) {
		fakeUser();
	}
}
