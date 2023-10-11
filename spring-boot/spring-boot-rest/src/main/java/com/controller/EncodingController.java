package com.controller;

import java.time.Duration;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

/**
 * server.servlet.encoding.charset=UTF-8
 * server.servlet.encoding.force-response=true
 */
@RestController
public class EncodingController {

	@GetMapping(value = "/encoding", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public ResponseEntity<Flux<?>> getFlux() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Title", "Your Title Here");

		Flux<String> dataFlux = Flux.just("Text", "関連当", "안녕하세요", "ไม้สามัญ").delayElements(Duration.ofMillis(500));

		return ResponseEntity.ok() //
				.headers(headers) //
				.body(dataFlux);
	}

}
