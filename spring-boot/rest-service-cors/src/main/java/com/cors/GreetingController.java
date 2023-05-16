package com.cors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class GreetingController {

	@Autowired
	private HttpServletRequest request;

	private final AtomicLong counter = new AtomicLong();
	private Map<String, Object> content = new HashMap<>();

	@GetMapping("greeting")
	public Map<String, Object> greeting() {
		content.put("id", counter.incrementAndGet());
		content.put("content", String.format("Hello, %s!", counter.get()));
		String clientIp = request.getHeader("X-Real-IP");
		if (clientIp == null || clientIp.isEmpty()) {
			clientIp = request.getRemoteAddr();
		}
		System.out.println("Request IP: " + clientIp);
		return content;
	}

}
