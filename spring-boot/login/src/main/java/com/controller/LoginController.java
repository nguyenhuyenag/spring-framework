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

	@GetMapping({ "/", "home" })
	public String home() {
		return "home";
	}
	
	@GetMapping("info")
	public String info(ModelMap model, HttpSession session, HttpServletRequest request) {
		Account account = checkCookie(request);
		if (account == null) {
			// model.put("account", new Account());
			return "redirect:/login";
		}
//		AccountModel accountModel = new AccountModel();
//		if (accountModel.login(account.getUsername(), account.getPassword())) {
//			session.setAttribute("username", account.getUsername());
//			return "account/welcome";
//		} else {
//			model.put("error", "Account's Invalid");
//			return "account/index";
//		}
		return "info";
	}

//	@GetMapping("welcome")
//	public String welcome() {
//		return "welcome";
//	}

	@GetMapping("login")
	public String login(@ModelAttribute("account") Account account) {
		return "login";
	}

	@PostMapping("login")
	public String login(@ModelAttribute("account") Account account, ModelMap model, HttpSession session,
			HttpServletRequest req, HttpServletResponse res) {
		AccountModel accountModel = new AccountModel();
		if (accountModel.login(account.getUsername(), account.getPassword())) {
			session.setAttribute("username", account.getUsername());
			if (req.getParameter("remember") != null) {
				Cookie ckUsername = new Cookie("username", account.getUsername());
				ckUsername.setMaxAge(3600);
				res.addCookie(ckUsername);
				Cookie ckPassword = new Cookie("password", account.getPassword());
				ckPassword.setMaxAge(3600);
				res.addCookie(ckPassword);
				// session = req.getSession(true);
				// session.setAttribute("username", webSession);
			}
			return "home";
		}
		model.put("error", "Account's Invalid");
		return "login";
	}

	@GetMapping(value = "logout")
	public String logout(HttpSession session, HttpServletRequest req, HttpServletResponse re) {
		// Remove session
		session.removeAttribute("username");
		// Remove cookie
		for (Cookie cookie : req.getCookies()) {
			if (cookie.getName().equalsIgnoreCase("username")) {
				cookie.setMaxAge(0);
				re.addCookie(cookie);
			}
			if (cookie.getName().equalsIgnoreCase("password")) {
				cookie.setMaxAge(0);
				re.addCookie(cookie);
			}
		}
		return "redirect:/home";
	}

	public Account checkCookie(HttpServletRequest request) {
		String username = "", password = "";
		Cookie[] cookies = request.getCookies();
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
