package dev.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeadersController {

	@GetMapping("listHeaders")
	private ResponseEntity<?> listHeaders(@RequestHeader Map<String, String> header) {
		return ResponseEntity.ok(header);
	}

}
