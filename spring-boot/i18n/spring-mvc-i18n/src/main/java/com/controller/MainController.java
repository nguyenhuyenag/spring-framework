package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@RequestMapping(value = { "/", "/login1" })
	public String staticResource(Model model) {
		return "login1";
	}

	// http://localhost:8080/en/login2
	@RequestMapping(value = "/{locale:en|fr|vi}/login2")
    public String login2(@PathVariable String locale) {
        return "login2";
    }

}
