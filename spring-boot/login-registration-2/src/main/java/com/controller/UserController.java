package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@GetMapping("get-user")
	public String loginPage(Model model, Principal principal, Authentication authentication, HttpServletRequest req) {
		// model.addAttribute("username", fromBean());
		model.addAttribute("username", principal.getName());
		// model.addAttribute("username", authentication.getName());
		// model.addAttribute("username", fromHttpRequest(req));
		return "get-user";
	}

	public String fromBean() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			return authentication.getName();
		}
		return "";
	}
	
	public String fromHttpRequest(HttpServletRequest req) {
		Principal principal = req.getUserPrincipal();
        return principal.getName();
	}
	
}
