package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandleController {
	
	@GetMapping("request")
	public String request() {
		return "request";
	}
	
	@GetMapping("pass-data/page-1")
	public String index() {
		return "pass-data/page-1";
	}
	
	@GetMapping("pass-data/page-2")
	public String passData() {
		return "pass-data/page-2";
	}
	
}
