package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.model.ColorForm;
import com.model.DBUtils;
import com.model.Dept;
import com.model.Language;

@Controller
@RequestMapping("core")
public class CoreController {

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
		return "core/for-each";
	}

	@GetMapping("el")
	public String el(Model model) {
		model.addAttribute("title", "Expression Language");
		return "core/el";
	}

	@GetMapping("if")
	public String ifView(Model model) {
		List<Dept> list = DBUtils.queryDepartments();
		model.addAttribute("title", "If Condition");
		model.addAttribute("departments", list);
		return "core/if";
	}

	@GetMapping("if-else")
	public ModelAndView ifelseView(Model model) {
		model.addAttribute("title", "If Else Condition");
		return new ModelAndView("core/if-else", "colorForm", new ColorForm());
	}

	@PostMapping("if-else")
	public ModelAndView submit(@Valid @ModelAttribute("colorForm") ColorForm colorForm, ModelMap model) {
		//BindingResult result, 
//		if (result.hasErrors()) {
//			return new ModelAndView("error");
//		}
		model.addAttribute("title", "If Else Condition");
		model.addAttribute("colorForm", colorForm);
		return new ModelAndView("core/if-else");
	}
	
	@GetMapping("cout")
	public String cout(Model model) {
		String name = "I'm Java";
		String mail = null;
		model.addAttribute("title", "Cout");
		model.addAttribute("name", name);
		model.addAttribute("mail", mail);
		return "core/cout";
	}
	
	@GetMapping("set")
	public String setView(Model model) {
		ColorForm color = new ColorForm();
		model.addAttribute("title", "c:set");
		model.addAttribute("colorObject", color);
		return "core/set";
	}
	
	@GetMapping("set2")
	public String set2View(Model model) {
		model.addAttribute("title", "c:set 2");
		return "core/set2";
	}
	
	@GetMapping("remove")
	public String removeView(Model model) {
		model.addAttribute("title", "c:remove");
		return "core/remove";
	}
	
	@GetMapping("catch")
	public String catchView(Model model) {
		model.addAttribute("title", "c:catch");
		return "core/catch";
	}
	
	@GetMapping("for-tokens")
	public String forTokensView(Model model) {
		model.addAttribute("title", "c:forTokens");
		return "core/for-tokens";
	}
	
	@GetMapping("url")
	public String urlView(Model model) {
		model.addAttribute("title", "c:url");
		return "core/url";
	}
	
	@GetMapping("import")
	public String importView(Model model) {
		model.addAttribute("title", "c:import");
		return "core/import";
	}
	
	@GetMapping("redirect")
	public String redirectView(Model model) {
		model.addAttribute("title", "c:redirect");
		return "core/redirect";
	}
	
	@GetMapping("param")
	public String param(Model model) {
		model.addAttribute("title", "c:param");
		return "core/param";
	}
	
}
