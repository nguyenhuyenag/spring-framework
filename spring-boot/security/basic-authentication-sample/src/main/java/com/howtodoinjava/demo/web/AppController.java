package com.howtodoinjava.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

	// @Autowired
	// private AppBasicAuthenticationEntryPoint logoutHandler;

	/*
	 * @GetMapping("/") public String home() { return "Hello World !!"; }
	 */

	@GetMapping("/public")
	public String publicResource() {
		return "Access allowed to all !!";
	}
}
