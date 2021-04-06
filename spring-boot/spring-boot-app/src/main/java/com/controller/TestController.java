package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.util.PropertiesReader;

@RestController
@RequestMapping("test")
public class TestController {
	
	@GetMapping("my-test")
	public String test() {
		return "Value: " + PropertiesReader.JWT_EXPIRATION_TIME;
	}
	
}
