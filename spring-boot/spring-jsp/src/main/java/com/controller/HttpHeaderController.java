package com.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpHeaderController {

	@GetMapping("/listHeaders")
	public ResponseEntity<String> listAllHeaders(@RequestHeader Map<String, String> headers) {
		headers.forEach((key, value) -> {
			System.out.println(String.format("Header '%s' = %s", key, value));
		});
		return ResponseEntity.ok(String.format("Listed %d headers", headers.size()));
	}

}
