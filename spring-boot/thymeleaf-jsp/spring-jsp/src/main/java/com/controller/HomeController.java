package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "home" })
	public String index(Model model) {
		String message = "Spring Boot + JSP";
		model.addAttribute("message", message);
		return "home";
	}
	
	@GetMapping("test")
	public String test(Model model) {
		model.addAttribute("title", "Tags and Directives");
		return "test";
	}

}
