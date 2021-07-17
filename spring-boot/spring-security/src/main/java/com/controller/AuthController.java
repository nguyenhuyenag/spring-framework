package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.util.WebUtils;

@Controller
public class AuthController {

	@GetMapping("login")
	public String loginPage(HttpServletRequest req) {
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			return "redirect:home";
		}
		return "login";
	}

	@GetMapping("logout-successful")
	public String logoutSuccessfulPage() {
		return "logout-successful";
	}
	
	@GetMapping("user-info")
	public String userInfo(Model model, Principal principal) {
		// Sau khi user login thanh cong se co principal
		String username = principal.getName();
		System.out.println("User Name: " + username);
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "user-info";
	}
	
	@GetMapping("admin")
	public String adminPage(Model model, Principal principal) {
		User loginedUser = (User) ((Authentication) principal).getPrincipal();
		String userInfo = WebUtils.toString(loginedUser);
		model.addAttribute("userInfo", userInfo);
		return "admin";
	}
	
	@GetMapping("403")
	public String accessDenied(Model model, Principal principal) {
		if (principal != null) {
			User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(loginedUser);
			model.addAttribute("userInfo", userInfo);
			String message = "Hi " + principal.getName() + "<br/> You do not have permission to access this page!";
			model.addAttribute("message", message);
		}
		return "403";
	}

}
