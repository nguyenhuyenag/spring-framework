package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping({ "/", "home" })
	public String homePage(Model model) {
		return "home";
	}

	@GetMapping("contact")
	public String contact(Model model) {
		return "contact";
	}

}
