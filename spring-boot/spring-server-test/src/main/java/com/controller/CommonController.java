package com.controller;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.response.Response;

@RestController
@RequestMapping("v1")
public class CommonController {
	
	@Autowired
	private HttpServletRequest request;

	@GetMapping("slow-service")
	private String slowService(@RequestParam(defaultValue = "0", required = false) int delay) {
		try {
			TimeUnit.SECONDS.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "I'm a slow service";
	}

	@GetMapping("do-test")
	public ResponseEntity<?> doTest() {
		Response response = new Response() //
				.withPath(request.getRequestURI()) //
				.withTimestamp(System.currentTimeMillis()) //
				.withCode(HttpStatus.CREATED.value()) //
				.withMessage("User created successfully");
		URI location = URI.create("/users/1111");
		return ResponseEntity.created(location).body(response);
	}

}
