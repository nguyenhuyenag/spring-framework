package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Account;
import com.models.AccountModel;

@Controller
public class LoginController {

	@GetMapping("welcome")
	public String welcome() {
		return "welcome";
	}

	@GetMapping("login")
	public String login(@ModelAttribute("account") Account account) {
		return "login";
	}

	@PostMapping("login")
	public String login(@ModelAttribute("account") Account account, ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response) {
		AccountModel accountModel = new AccountModel();
		if (accountModel.login(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			if (request.getParameter("remember") != null) {
				Cookie ckUsername = new Cookie("username", account.getUsername());
				ckUsername.setMaxAge(3600);
				response.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password", account.getPassword());
				ckPassword.setMaxAge(3600);
				response.addCookie(ckPassword);
			}
			return "welcome";
		}
		model.put("error", "Account's Invalid");
		return "login";
	}

	@GetMapping(value = "logout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		// Remove session
		session.removeAttribute("username");
		// Remove cookie
		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		return "redirect:/login";
	}

	public Account checkCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		// Account account = null;
		String username = "", password = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				username = cookie.getValue();
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				password = cookie.getValue();
			}
		}
		if (!username.isEmpty() && !password.isEmpty()) {
			return new Account(username, password);
		}
		return null;
	}

}
