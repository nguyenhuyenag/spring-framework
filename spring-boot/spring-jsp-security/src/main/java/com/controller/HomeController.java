package com.controller;

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

}
