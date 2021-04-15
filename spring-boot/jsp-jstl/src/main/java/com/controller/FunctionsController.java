package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class FunctionsController {

	@GetMapping("functions")
	public String functions(Model model) {
		model.addAttribute("title", "Functions");
		return "functions/functions";
	}
	
}
