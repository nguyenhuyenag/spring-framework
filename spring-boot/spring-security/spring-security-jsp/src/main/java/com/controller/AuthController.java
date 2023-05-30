package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("login")
	public String loginPage(Principal principal, HttpServletRequest request) {
		// Principal principal = req.getUserPrincipal();
		// String referer = request.getHeader("Referer"); //Get previous URL before call
		// '/login'
		// String referer =
		// request.getAttribute(RequestDispatcher.FORWARD_REQUEST_URI).toString();
		// String referer = request.getHeader(HttpHeaders.REFERER);
		// Object forward = request.getAttribute("javax.servlet.forward.request_uri");
		// System.out.println("referer: " + referer);
		// System.out.println("forward: " + forward);
		// System.out.println("pReferer: " + pReferer);
		Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		// LOG.info("savedRequest: {}", savedRequest);
		System.out.println("savedRequest: " + savedRequest);
		// request.getSession().setAttribute(CustomAuthenticationSuccessHandler.REDIRECT_URL_SESSION_ATTRIBUTE_NAME,
		// referer);
		return principal == null ? "login" : "redirect:/";
	}

}
