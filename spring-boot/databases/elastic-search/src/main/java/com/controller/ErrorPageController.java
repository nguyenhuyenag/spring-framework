package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorPageController {
	
	@GetMapping("404")
	public String notFound(Model model) {
		return "404";
	}

	@GetMapping("405")
	public String forbidden(Model model) {
		return "405";
	}

//	@GetMapping("403")
//	public String forbidden(Model model) {
//		return "error/403";
//	}
//

//
//	@GetMapping("500")
//	public String internal(Model model) {
//		return "error/500";
//	}
//
//	@GetMapping("/access-denied")
//	public String accessDenied() {
//		return "error/access-denied";
//	}

}
