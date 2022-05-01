package com.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "home" })
	public String homePage(Model model) {
		model.addAttribute("title", "Home");
		model.addAttribute("message", "This is Home page!");
		return "home";
	}
	
	@GetMapping("security-taglib") // Spring Security - Taglib
	public String securityTaglib(Model model, Principal principal) {
		if (principal != null) {
			
		}
		return "security-taglib";
	}

}
