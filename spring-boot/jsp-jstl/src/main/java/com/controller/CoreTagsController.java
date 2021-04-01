package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.Language;

@Controller
public class CoreTagsController {

	private static List<Language> list = new ArrayList<>();

	static {
		list.add(new Language("C"));
		list.add(new Language("C#"));
		list.add(new Language("C++"));
		list.add(new Language("PHP"));
		list.add(new Language("Java"));
		list.add(new Language("Python"));
		list.add(new Language("Javascript"));
	}

	@GetMapping("for-each")
	public String viewPersonList(Model model) {
		model.addAttribute("title", "For Each");
		model.addAttribute("languages", list);
		return "for-each";
	}

	@GetMapping("el")
	public String el(Model model) {
		model.addAttribute("title", "Expression Language");
		return "el";
	}
	
	@GetMapping("if")
	public String ifView(Model model) {
		model.addAttribute("title", "If Condition");
		return "if";
	}

}
