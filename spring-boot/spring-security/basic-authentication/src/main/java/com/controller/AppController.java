package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/public")
	public String publicResource() {
		return "Allowed to all";
	}

	@GetMapping(value = "/user")
	public ResponseEntity<String> guest() {
		System.out.println("Showing guest page.");
		return new ResponseEntity<>("Hello from guest page!", HttpStatus.OK);
	}

	@GetMapping(value = "/admin")
	public ResponseEntity<String> admin() {
		System.out.println("Showing administrator page.");
		return new ResponseEntity<>("Welcome to secure/admin page!", HttpStatus.OK);
	}

}
