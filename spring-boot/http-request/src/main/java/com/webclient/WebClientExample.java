package com.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public class WebClientExample {

	private static void ex1() {
		WebClient webClient = WebClient.create("https://api.example.com");
		String response = webClient.get() //
				.uri("/data") //
				.retrieve() //
				.bodyToMono(String.class) //
				.block();
		System.out.println(response);
	}

	private static void ex2() {
		WebClient webClient = WebClient.create("https://api.example.com");
		webClient.get() //
				.uri("/data") //
				.retrieve() //
				.bodyToMono(String.class) //
				.subscribe(response -> {
					System.out.println(response);
				});
	}

	public static void main(String[] args) {
		ex1();
		ex2();
	}
}
