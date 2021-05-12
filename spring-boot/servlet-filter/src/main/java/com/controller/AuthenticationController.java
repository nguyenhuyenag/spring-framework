package com.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.User;
import com.service.UserService;
import com.util.WebUtils;

@Controller
public class AuthenticationController {

	@GetMapping("login")
	public String login() {
		return "login";
	}

	@PostMapping("login")
	public String login(HttpServletRequest request) {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User userAccount = UserService.findUser(username, password);
		if (userAccount == null) {
			String errorMessage = "Invalid Username or Password";
			request.setAttribute("errorMessage", errorMessage);
			return "login";
		}
		WebUtils.storeLoginedUser(request.getSession(), userAccount);
		String redirectId = request.getParameter("redirectId");
		String requestUri = WebUtils.getRedirectAfterLoginUrl(redirectId);
		if (StringUtils.isNotEmpty(requestUri)) {
			return "redirect:" + requestUri;
		}
		// Mặc định sau khi đăng nhập thành công chuyển hướng về trang /userInfo
		return "redirect:/userInfo";
	}

	@GetMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
