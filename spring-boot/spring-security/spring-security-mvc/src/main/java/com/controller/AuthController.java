package com.controller;

import java.security.Principal;
import java.util.Optional;

import com.entity.History;
import com.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController {

    private final HistoryRepository historyRepository;

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

//	Object savedRequest = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
//	if (savedRequest instanceof DefaultSavedRequest) {
//		DefaultSavedRequest defaultSavedRequest = (DefaultSavedRequest) savedRequest;
//		// String fullContextPath2 = request.getRequestURL().toString();
//		// System.out.println("[AuthController] fullContextPath: " + fullContextPath2);
//		if (!"/".equals(defaultSavedRequest.getRequestURI())) {
//			LOG.info("Redirect from: {}", defaultSavedRequest.getRedirectUrl());
//			// String nextId = WebUtils.setNextPage(defaultSavedRequest.getRedirectUrl());
//			// return "redirect:/login?next=" + nextId; // Corrected line
//			// response.sendRedirect("./login?next=" + nextId);
//		}
//	}

    @GetMapping("login")
    public String login(Principal principal) {
        if (principal == null) {
            // System.out.println("[" + this.getClass().getSimpleName() + "] No Login");
        } else {
            // System.out.println("[" + this.getClass().getSimpleName() + "] Already Login");
            // return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/login-token")
    public String loginToken() {
        return "login-token";
    }

    @PostMapping("/login-token")
    public String loginToken(@RequestParam("tokenId") String tokenId) {
        System.out.println("tokenId = " + tokenId);
        Optional<History> byTokenId = historyRepository.findByTokenId(tokenId);
        if (byTokenId.isEmpty()) {
            log.error("TokenId={} not found", tokenId);
            return null;
        }
        return "login-token";
    }

}
