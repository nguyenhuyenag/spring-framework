package com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		int page = ServletRequestUtils.getIntParameter(req, "page", 1);
		String search = ServletRequestUtils.getStringParameter(req, "search", "");
		Page<Vocabulary> listPage = service.pagination(page - 1);
		if (StringUtils.isNotEmpty(search)) {
			listPage = service.searchByWord(search, page);
		}
		page = page < 1 ? 1 : page;
		model.addAttribute("listData", listPage.getContent());
		model.addAttribute("CURRENT_PAGE", page);
		model.addAttribute("TOTAL", listPage.getTotalPages());
		return "vocabulary";
	}
	
	@GetMapping("plural-noun")
	public String pluralNoun(Model model) {
		return "plural-noun";
	}
	
	@ResponseBody
	@PostMapping("plural-noun")
	public String pluralNouns(Model model, String noun) {
		return service.pluralNoun(noun);
	}

}
