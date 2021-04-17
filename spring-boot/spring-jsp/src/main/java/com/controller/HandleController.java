package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandleController {
	
	@GetMapping("request")
	public String request() {
		return "request";
	}
	
}
