package com.spring;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Async -> Cho phép các phương thức được gọi bất đồng bộ (asynchronously) thay
 *        vì đồng bộ (synchronously).
 * 
 *        -> Khi một phương thức được đánh dấu với @Async, Spring Framework sẽ
 *        tạo ra một thread riêng để thực thi phương thức đó. Sau đó, phương
 *        thức sẽ tiếp tục chạy bất đồng bộ trong thread mới và không chờ đợi
 *        cho đến khi hoàn thành.
 */
@Service
public class AsyncMethod {

	private static final Logger logger = LoggerFactory.getLogger(AsyncMethod.class);

	private final RestTemplate restTemplate;

	public AsyncMethod(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException {
		logger.info("findUser -> " + Thread.currentThread().getName());
		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(results);
	}

	public User findUserWithoutAsync(String user) throws InterruptedException {
		logger.info("findUserWithoutAsync -> " + Thread.currentThread().getName());
		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		return results;
	}

}
