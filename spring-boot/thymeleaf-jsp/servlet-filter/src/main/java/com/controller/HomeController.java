package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping({ "/", "home" })
	public String home() {
		return "home";
	}

	@GetMapping("employee")
	public String employee() {
		return "employee";
	}

	@GetMapping("manager")
	public String manager() {
		return "manager";
	}

	@GetMapping("userInfo")
	public String userInfo() {
		return "userInfo";
	}
	
	@GetMapping("accessDenied")
	public String accessDenied() {
		return "accessDenied";
	}

}
