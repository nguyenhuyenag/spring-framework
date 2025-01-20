package com.webclient;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class WebClientParams {

	public static void main(String[] args) {
		WebClient webClient = WebClient.builder() //
				.baseUrl("https://example.com/api") //
				.build();

		/**
		 * URI Path Component
		 */

		// => https://example.com/api/products
		webClient.get() //
				.uri("/products") //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

		// => /products/2/attributes/13
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/{id}/attributes/{attributeId}") //
						.build(2, 13)) //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

		/**
		 * URI Query Parameters
		 */

		// => /products/?name=AndroidPhone&color=black&deliveryDate=13/04/2019
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/") //
						.queryParam("name", "AndroidPhone") //
						.queryParam("color", "black") //
						.queryParam("deliveryDate", "13/04/2019") //
						.build()) //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

		// => /products/?name=AndroidPhone&color=black&deliveryDate=13%2F04%2F2019
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/") //
						.queryParam("name", "{title}") //
						.queryParam("color", "{authorId}") //
						.queryParam("deliveryDate", "{date}") //
						.build("AndroidPhone", "black", "13/04/2019")) //
				.retrieve() //
				.bodyToMono(String.class) //
				.block();

		/**
		 * Array Parameters
		 */

		// => /products/?category=Phones&category=Tablets
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/") //
						.queryParam("category", "Phones", "Tablets") //
						.build()) //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

		// => /products/?tag%5B%5D=Snapdragon&tag%5B%5D=NFC
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/") //
						.queryParam("tag[]", "Snapdragon", "NFC") //
						.build()) //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

		// => /products/?category=Phones,Tablets
		webClient.get() //
				.uri(uriBuilder -> uriBuilder //
						.path("/products/") //
						.queryParam("category", String.join(",", "Phones", "Tablets")) //
						.build()) //
				.retrieve() //
				.bodyToMono(String.class) //
				.onErrorResume(e -> Mono.empty()) //
				.block();

	}

}
