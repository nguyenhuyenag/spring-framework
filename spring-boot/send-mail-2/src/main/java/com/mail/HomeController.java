package com.mail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@GetMapping("home")
	public String request() {
		return "home";
	}
	
	@GetMapping("home1")
	public ModelAndView request1() {
		return new ModelAndView("home1");
	}

}
