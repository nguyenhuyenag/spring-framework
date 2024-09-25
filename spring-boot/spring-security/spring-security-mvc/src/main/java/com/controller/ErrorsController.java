package com.controller;

import java.security.Principal;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.util.WebUtils;

@Controller
public class ErrorsController implements ErrorController {

	@Override
	public String getErrorPath() {
		return null;
	}

	@RequestMapping("error")
	public String handleError(HttpServletRequest request, HttpServletResponse response) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			System.out.println("ErrorsController -> Status: " + response.getStatus());
			int statusCode = Integer.parseInt(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "404";
			}
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "500";
			}
		}
		return "error";
	}

	@GetMapping("403")
	public String accessDenied(Model model, Principal principal2) {
		// String username;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			UserDetails userDetails = (UserDetails) principal;
			// User loginedUser = (User) ((Authentication) principal).getPrincipal();
			String userInfo = WebUtils.toString(userDetails);
			model.addAttribute("userInfo", userInfo);
		} else {
			// username = principal.toString();
		}
		// System.out.println("username" + username);
		return "403";
	}

	@GetMapping("404")
	public String notfound(Model model) {
		model.addAttribute("message", "Page not found");
		return "404";
	}

}
