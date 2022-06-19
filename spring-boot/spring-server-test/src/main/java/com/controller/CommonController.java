package com.controller;

import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
public class CommonController {

	@GetMapping("slow-service")
	private String slowService(@RequestParam(defaultValue = "0", required = false) int delay) {
		try {
			TimeUnit.SECONDS.sleep(delay);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "I'm a slow service";
	}

}
