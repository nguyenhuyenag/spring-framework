package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

	@ResponseBody
	@RequestMapping(value = "basic", method = RequestMethod.GET)
	private String basic() {
		return "I'm @Controller";
	}

}
