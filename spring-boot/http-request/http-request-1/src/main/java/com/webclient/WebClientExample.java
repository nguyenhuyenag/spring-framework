package com.webclient;

import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

/*-
 *  WebClient webClient = WebClient.create("http://localhost:8080");
  	Mono<User> user = webClient.get()
				        .uri("/user/{id}", id)
				        .retrieve()
				        .bodyToMono(User.class);
				        
  	@Bean
	public WebClient webClient() {
    return WebClient.builder()
      		.baseUrl("http://localhost:8080")
      		.clientConnector(new ReactorClientHttpConnector(
        		HttpClient.create().responseTimeout(Duration.ofMillis(250))))
      		.build();
	}

  	webClient.get()
  		.uri(uriBuilder -> uriBuilder.path("/author/transactional")
  									 .queryParam("title", title)
  									 .build())
  		.retrieve()
  		.bodyToMono(String.class)
  		.block();
  		
  	
  	WebClient webClient = WebClient.builder()
  			.filter(ExchangeFilterFunctions.basicAuthentication("username", "password"))
  			.build();
 */
public class WebClientExample {

	protected static String URL = "https://random-data-api.com/api/v2/credit_cards";

	protected static void ex1() {
		ExchangeFilterFunction filterFunction = (clientRequest, nextFilter) -> {
			System.out.println("WebClient fitler executed");
			HttpMethod httpMethod = clientRequest.method();
		    if (httpMethod == HttpMethod.GET) {
		        System.out.println("GET METHOD");
		    }
		    String oldUrl = clientRequest.url().toString();
		    System.out.println("URL: " + oldUrl);
			return nextFilter.exchange(clientRequest);
		};

		WebClient webClient = WebClient.builder() //
				.baseUrl("https://random-data-api.com/") //
				.filter(filterFunction) //
				.build();

		String response = webClient.get() //
				.uri("/api/v2/credit_cards") //
				.retrieve() //
				.bodyToMono(String.class) //
				.block();
		
		System.out.println(response);
	}

	public static void main(String[] args) {
		ex1();
	}
}
