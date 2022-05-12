package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "home" })
	public String login() {
		return "home";
	}

	@GetMapping("security-taglib") // Spring Security - Taglib
	public String securityTaglib() {
		return "security-taglib";
	}

}
