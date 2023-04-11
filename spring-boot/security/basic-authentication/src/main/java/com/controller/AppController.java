package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/public")
	public String publicResource() {
		return "Allowed to all";
	}

	@GetMapping("/user")
	public String user() {
		return "User data";
	}

}
