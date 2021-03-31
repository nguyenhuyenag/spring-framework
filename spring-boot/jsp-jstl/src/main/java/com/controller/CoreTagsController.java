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
		String msg = "Ngôn ngữ lập trình";
		model.addAttribute("msg", msg);
		model.addAttribute("languages", list);
		return "for-each";
	}

	@GetMapping("el")
	public String core() {
		return "el";
	}

}
