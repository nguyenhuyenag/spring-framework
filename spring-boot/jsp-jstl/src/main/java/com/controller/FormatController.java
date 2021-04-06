package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("format")
public class FormatController {

	@GetMapping("bundle")
	public String bundle() {
		return "format/bundle";
	}

}
