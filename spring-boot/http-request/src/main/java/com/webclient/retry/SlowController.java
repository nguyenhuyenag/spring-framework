package com.webclient.retry;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {

	@GetMapping("slow-service")
	public ResponseEntity<?> slowService() throws InterruptedException {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Java");
		map.put("version", "16");
		map.put("company", "Oracle");
		TimeUnit.SECONDS.sleep(30);
		return ResponseEntity.ok(map);
	}

}
