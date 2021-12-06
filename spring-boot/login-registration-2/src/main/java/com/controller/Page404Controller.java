package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// https://stackoverflow.com/questions/38777723/how-i-create-an-error-handler-404-500-in-spring-boot-mvc
@Controller
public class Page404Controller {

	@GetMapping("404")
	public String pageNotFound() {
		return "error/404";
	}

}
