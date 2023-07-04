package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.util.WebUtils;

@Controller
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

//	private boolean isAuthenticated() {
//	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	    if (authentication == null || AnonymousAuthenticationToken.class.
//	      isAssignableFrom(authentication.getClass())) {
//	        return false;
//	    }
//	    return authentication.isAuthenticated();
//	}
//	
//	@GetMapping("/loginUser")
//	public String getUserLoginPage() {
//	    if (isAuthenticated()) {
//	        return "redirect:userMainPage";
//	    }
//	    return "loginUser";
//	}

	@GetMapping("login")
	public String loginPage(Principal principal, HttpServletRequest request) {
		// Principal principal = req.getUserPrincipal();
		// System.out.println("[AuthController] principal: " + principal.getName());
		if (principal == null) {
			System.out.println("[AuthController] No Login");
		} else {
			System.out.println("[AuthController] Already Login");
			return "redirect:/home";
		}
		Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		if (savedRequest instanceof DefaultSavedRequest) {
			DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;

			StringBuffer requestURL = request.getRequestURL();
			String contextPath = request.getContextPath();
			String fullContextPath = requestURL.substring(0, requestURL.indexOf(contextPath) + contextPath.length());
			System.out.println("[AuthController] fullContextPath: " + fullContextPath);

			String fullContextPath2 = request.getRequestURL().toString();
			System.out.println("[AuthController] fullContextPath2: " + fullContextPath2);

			if (!"/".equals(defaultSavedRequest.getRequestURI())) {
				LOG.info("Redirect from: {}", defaultSavedRequest.getRedirectUrl());
				String nextId = WebUtils.setNextPage(defaultSavedRequest.getRedirectUrl());
			}
		}
		return principal == null ? "login" : "redirect:/";
	}

}
