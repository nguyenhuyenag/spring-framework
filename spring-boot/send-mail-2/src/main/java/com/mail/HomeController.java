package com.mail;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping(value = { "/", "home" })
	private String home(Model model) {
		List<String> list = new ArrayList<>();
		list.add("send-mail");
		list.add("send-mail-cc");
		list.add("send-mail-bcc");
		model.addAttribute("listUrls", list);
		return "home";
	}

}
