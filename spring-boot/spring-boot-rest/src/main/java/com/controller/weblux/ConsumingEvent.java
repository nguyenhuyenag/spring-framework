package com.controller.weblux;

import java.time.LocalTime;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
public class ConsumingEvent {
	
	public static void main(String[] args) {
		WebClient client = WebClient.create("http://localhost:8080/");
		ParameterizedTypeReference<ServerSentEvent<String>> type = new ParameterizedTypeReference<>() {};

		Flux<ServerSentEvent<String>> eventStream = client.get() //
				.uri("/stream-sse") //
				.retrieve() //
				.bodyToFlux(type);

		eventStream.subscribe(
			content -> log.info("Time: {} - event: name[{}], id [{}], content[{}] ", //
					LocalTime.now(),content.event(), content.id(), content.data()),
			error -> log.error("Error receiving SSE: {}", error), () -> log.info("Completed!!!")
		);
	}
	
}
