package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.DBUtils;
import com.model.Dept;
import com.model.Language;

@Controller
public class MainController {

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

	@GetMapping({ "/", "home" })
	public String index(Model model) {
		String message = "Spring Boot + JSP";
		model.addAttribute("message", message);
		return "home";
	}

	@GetMapping("language")
	public String viewPersonList(Model model) {
		String msg = "Ngôn ngữ lập trình";
		model.addAttribute("msg", msg);
		model.addAttribute("languages", list);
		return "language";
	}

	@GetMapping("core-example-01")
	public String core(Model model) {
		List<Dept> list = DBUtils.queryDepartments();
		model.addAttribute("departments", list);
		return "core-example-01";
	}

}
