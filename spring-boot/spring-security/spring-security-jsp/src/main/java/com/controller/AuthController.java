package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("login")
	public String loginPage(Principal principal, HttpServletRequest request) {
		// Principal principal = req.getUserPrincipal();
		Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest instanceof DefaultSavedRequest) {
			DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
			if (!"/".equals(defaultSavedRequest.getRequestURI())) {
				LOG.info("Url: {}", defaultSavedRequest.getRedirectUrl());
			}
		}
		return principal == null ? "login" : "redirect:/";
	}

}
