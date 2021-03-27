package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllers {

	@RequestMapping(value = "rest", method = RequestMethod.GET)
	private String rest() {
		return "I'm @RestController";
	}

	// Any request: POST, GET, DELETE, ...
	@RequestMapping({ "any", "any-request" })
	private String any() {
		return "I'm any request";
	}

}
