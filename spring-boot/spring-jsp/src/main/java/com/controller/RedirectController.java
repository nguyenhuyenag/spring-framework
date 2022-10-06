package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class RedirectController {

	@RequestMapping("page1")
	public String page1() {
		return "redirect/page1";
	}

	@RequestMapping("redirect")
	public RedirectView redirect(@RequestParam("name") String name, RedirectAttributes redirectAttributes) {
		redirectAttributes.addAttribute("name", name);
		return new RedirectView("page2");
	}

	@RequestMapping("page2")
	public String page2(@RequestParam("name") String name, Model model) {
		model.addAttribute("name", name.toUpperCase());
		return "redirect/page2";
	}

}
