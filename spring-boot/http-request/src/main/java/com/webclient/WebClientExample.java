package com.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientExample {
	
	protected static String URL = "https://random-data-api.com/api/v2/credit_cards";

	protected static void ex1() {
		WebClient webClient = WebClient.create(URL);
		String response = webClient.get() //
				// .uri("/data") //
				.retrieve() //
				.bodyToMono(String.class) //
				.block();
		System.out.println(response);
	}

	public static void main(String[] args) {
		ex1();
	}
}
