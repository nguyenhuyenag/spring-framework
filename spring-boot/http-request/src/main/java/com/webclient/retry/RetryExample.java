package com.webclient.retry;

import java.time.Duration;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

/**
 * retryWhen(Retry.max(3))
 * 
 * retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2)));
 */
public class RetryExample {

	public static void main(String[] args) {
		// Tạo một WebClient để thực hiện yêu cầu HTTP
		WebClient webClient = WebClient.builder() //
				.baseUrl("http://localhost:8080") //
				.build();

		// Thực hiện yêu cầu HTTP và tái thử khi có lỗi
		int maxRetries = 3;

		Retry retrySpec = Retry.backoff(maxRetries, Duration.ofSeconds(5)).doBeforeRetry(retrySignal -> {
			// Log retry information here
			System.out.println("Retrying... (" + retrySignal.totalRetries() + ")");
		});

		Mono<String> response = webClient.get() //
				.uri("/slow-service") //
				.retrieve() //
				.bodyToMono(String.class) //
				// .retry(maxRetries)
				.retryWhen(retrySpec);

		// Subscribe để thực hiện yêu cầu
		response.subscribe( //
				result -> System.out.println("Kết quả: " + result), //
				error -> System.err.println("Lỗi: " + error.getMessage()) //
		);
	}

}
