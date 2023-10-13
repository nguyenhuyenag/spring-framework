package com.controller;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.model.Tweet;

import reactor.core.publisher.Flux;

/*-
 * - So sánh RestTemplate và WebClient
 * 
 * 		+ RestTemplate blocking client
 * 
 * 		+ WebClient non-blocking client
 * 
 */
@RestController
public class WebClientRestTemplate {

	// private Logger log = LoggerFactory.getLogger(this.getClass());

	@Value("${server.port}")
	private int serverPort = 8080;

	private String getSlowServiceUri() {
		return "http://localhost:" + serverPort + "/slow-service-tweets";
	}

	@GetMapping("/slow-service-tweets")
	private ResponseEntity<List<Tweet>> getAllTweets() throws InterruptedException {
		Thread.sleep(2000L); // delay
		List<Tweet> listTweet = Arrays.asList( //
				new Tweet("RestTemplate rules", "@user1"), //
				new Tweet("WebClient is better", "@user2"), //
				new Tweet("OK, both are useful", "@user1"));
		return ResponseEntity.ok(listTweet);
	}

	@GetMapping("/tweets-blocking")
	public List<Tweet> getTweetsBlocking() {
		System.out.println("Starting BLOCKING Controller!");
		
		final String uri = getSlowServiceUri();

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Tweet>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tweet>>() {
				});

		List<Tweet> result = response.getBody();
		result.forEach(tweet -> System.out.println(tweet.toString()));
		System.out.println("Exiting BLOCKING Controller!");
		return result;
	}

	@GetMapping(value = "/tweets-non-blocking", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<?> getTweetsNonBlocking() {
		System.out.println("Starting NON-BLOCKING Controller!");
		
		Flux<Tweet> tweetFlux = WebClient.create() //
				.get() //
				.uri(getSlowServiceUri()) //
				.retrieve() //
				.bodyToFlux(Tweet.class);
		
		tweetFlux.subscribe(tweet -> System.out.println(tweet.toString()));
		System.out.println("Exiting NON-BLOCKING Controller!");
		
		return Flux.just("Data 1", "Data 2", "Data 3")
	            .delayElements(Duration.ofSeconds(1));
		
		//return tweetFlux;
	}

}
