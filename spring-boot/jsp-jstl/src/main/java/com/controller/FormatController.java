package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("format")
public class FormatController {

	@GetMapping("bundle")
	public String bundle() {
		return "format/bundle";
	}
	
	@GetMapping("format-number")
	public String formatNumber(Model model) {
		model.addAttribute("title", "fmt:formatNumber");
		return "format/format-number";
	}

}
