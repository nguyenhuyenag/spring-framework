package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MethodSecurityController {

	@Secured("ROLE_ADMIN")
	@GetMapping("method-security")
	public ResponseEntity<String> methodSecrity() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		String username = securityContext.getAuthentication().getName();
		return ResponseEntity.ok(username);
	}

	// @RolesAllowed({ "ROLE_USER", "ROLE_ADMIN" })
	// @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	@Secured("ROLE_ADMIN")
//	public String getUsername() {
//		SecurityContext securityContext = SecurityContextHolder.getContext();
//		return securityContext.getAuthentication().getName();
//	}

}
