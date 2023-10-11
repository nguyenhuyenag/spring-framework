package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	@GetMapping("/public")
	public Map<String, String> publicResource() {
		Map<String, String> map = new HashMap<>();
		map.put("data", "Allowed to all");
		return map;
	}

	@GetMapping(value = "/user")
	public ResponseEntity<?> guest() {
		Map<String, String> map = new HashMap<>();
		map.put("data", "Hello from user page!");
		return ResponseEntity.ok(map);
	}

	@GetMapping(value = "/admin")
	public ResponseEntity<?> admin() {
		Map<String, String> map = new HashMap<>();
		map.put("data", "Welcome to secure/admin page!");
		return ResponseEntity.ok(map);
	}

}
