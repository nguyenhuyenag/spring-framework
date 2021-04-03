package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.ColorForm;
import com.model.DBUtils;
import com.model.Dept;
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
		List<Dept> list = DBUtils.queryDepartments();
		model.addAttribute("title", "If Condition");
		model.addAttribute("departments", list);
		return "if";
	}

	// @GetMapping("if-else")
	// public String condition(Model model) {
	// model.addAttribute("title", "If Else Condition");
	// return "if-else";
	// }

	@GetMapping("if-else")
	public ModelAndView ifelseView(Model model) {
		model.addAttribute("title", "If Else Condition");
		return new ModelAndView("if-else", "colorForm", new ColorForm());
	}

	@PostMapping("if-else")
	public ModelAndView submit(@Valid @ModelAttribute("colorForm") ColorForm colorForm, //
			BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return new ModelAndView("error");
		}
		model.addAttribute("title", "If Else Condition");
		model.addAttribute("colorForm", colorForm);
		return new ModelAndView("if-else");
	}

}
