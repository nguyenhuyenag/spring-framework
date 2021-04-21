package com.boot.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.Info;

@Controller
public class WebController {

	@GetMapping("/") // Nếu người dùng request tới địa chỉ "/"
	public String index() {
		return "index"; // Trả về file index.html
	}

	@GetMapping("home")
	public String hello(@RequestParam(name = "name", defaultValue = "Java") String name, Model model) {
		model.addAttribute("name", name);
		return "home";
	}

	@GetMapping("profile")
	public String profile(Model model) {
		List<Info> list = new ArrayList<>();
		list.add(new Info("Nickname", "nguyenhuyenag"));
		list.add(new Info("Git", "https://github.com/nguyenhuyenag"));
		model.addAttribute("listProfile", list);
		// Trả về template profile.html
		return "profile";
	}
}
