package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	@GetMapping({ "/", "home" })
	public String homePage(Model model) {
		return "home";
	}

	@GetMapping("404")
	public String notFound(Model model) {
		return "404";
	}

	@GetMapping("405")
	public String forbidden(Model model) {
		return "405";
	}

}
