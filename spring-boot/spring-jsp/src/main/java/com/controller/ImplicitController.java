package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("implicit")
public class ImplicitController {

	@GetMapping("request")
	public String request(Model model) {
		model.addAttribute("title", "Request");
		return "implicit/request";
	}

}
