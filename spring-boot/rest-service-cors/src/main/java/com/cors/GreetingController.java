package com.cors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	private final AtomicLong counter = new AtomicLong();
	
	private Map<String, Object> content = new HashMap<>();

	@GetMapping("greeting")
	public Map<String, Object> greeting() {
		content.put("id", counter.incrementAndGet());
		content.put("content", String.format("Hello, %s!", counter.get()));
		return content;
	}

}
