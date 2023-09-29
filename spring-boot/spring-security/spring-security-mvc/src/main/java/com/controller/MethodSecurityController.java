package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MethodSecurityController {

	@GetMapping("method-security")
	public ResponseEntity<String> methodSecrity() {
		return ResponseEntity.ok("method-security");
	}

}
