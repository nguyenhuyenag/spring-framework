package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	@GetMapping({ "/", "login" })
	public String loginPage(HttpServletRequest req) {
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			// return "redirect:login";
		}
		return "login";
	}

}
