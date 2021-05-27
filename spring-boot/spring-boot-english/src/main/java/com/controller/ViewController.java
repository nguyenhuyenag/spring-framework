package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
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

	@GetMapping("vocabulary")
	public String vocabulary(Model model, HttpServletRequest req) {
		int page = ServletRequestUtils.getIntParameter(req, "page", 0);
		page = page < 0 ? 0 : page;
		Page<Vocabulary> listPage = service.pagination(page);
		model.addAttribute("listData", listPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("total", listPage.getTotalPages());
		return "vocabulary";
	}

}
