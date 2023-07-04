package com.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	// private final Logger LOG = LoggerFactory.getLogger(this.getClass());

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
	public String loginPage(Model model, Principal principal, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		if (principal != null) {
			System.out.println("[AuthController] Already Login");
			return "redirect:/home";
		}
		System.out.println("[AuthController] No Login");
//		Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//		if (savedRequest instanceof DefaultSavedRequest) {
//			DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
//			// String fullContextPath2 = request.getRequestURL().toString();
//			// System.out.println("[AuthController] fullContextPath: " + fullContextPath2);
//			if (!"/".equals(defaultSavedRequest.getRequestURI())) {
//				LOG.info("Redirect from: {}", defaultSavedRequest.getRedirectUrl());
//				// String nextId = WebUtils.setNextPage(defaultSavedRequest.getRedirectUrl());
//				// return "redirect:/login?next=" + nextId; // Corrected line
//				// response.sendRedirect("./login?next=" + nextId);
//			}
//		}
		return "login";
	}

}
