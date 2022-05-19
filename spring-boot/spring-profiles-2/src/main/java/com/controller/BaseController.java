package com.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

	@Value("${environment}")
	private String environment;

	@RequestMapping("/")
	public String index(Model model) {
		return environment;
	}

}
