package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.util.DateTimeUtils;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private RestTemplate restTemplate;

	private static final String URL = "https://jsonplaceholder.typicode.com/todos";

	@GetMapping("timestamp")
	private ResponseEntity<String> now() {
		String now = DateTimeUtils.getNow();
		return new ResponseEntity<>(now, HttpStatus.OK);
	}

	@GetMapping("get-json")
	private ResponseEntity<String> getJson() {
		String json = restTemplate.getForObject(URL, String.class);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

}
