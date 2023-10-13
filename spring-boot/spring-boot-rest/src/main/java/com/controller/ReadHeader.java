package com.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReadHeader {

	@GetMapping("read-header")
	public ResponseEntity<?> readHeader(@RequestHeader Map<String, String> headers) {
		// headers.forEach((k, v) -> System.out.println(k + " : " + v));
		headers.forEach((key, value) -> {
	        System.out.println(String.format("Header '%s' = %s", key, value));
	    });
		return ResponseEntity.ok(headers);
	}

}
