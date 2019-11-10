package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.filter.TokenHandler;

@Controller
@RequestMapping("api/auth")
public class JWTController {

	@PostMapping("logout")
	private ResponseEntity<Void> register(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		String username = TokenHandler.parseToken(token);
		System.out.println(username);
		System.out.println(token);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
