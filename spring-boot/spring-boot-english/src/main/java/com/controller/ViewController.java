package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.entity.Vocabulary;
import com.service.VocabService;

@Controller
public class ViewController {
	
	@Autowired
	private VocabService service;

	@GetMapping({ "/", "random" })
	public String homePage(Model model) {
		return "random";
	}

	@GetMapping("incomplete")
	public String contact(Model model) {
		List<Vocabulary> data = service.incompleteVocabulary();
		model.addAttribute("listData", data);
		return "incomplete"; // <-- tiles name
	}

}
