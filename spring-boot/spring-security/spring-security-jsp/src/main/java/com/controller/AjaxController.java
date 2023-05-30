package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.repository.UserRepository;

@Controller
public class AjaxController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/ajax")
	public String ajaxView() {
		return "ajax";
	}

	@ResponseBody
	@PostMapping("/api/my-ajax")
	public ResponseEntity<?> ajaxPost(String name) {
		System.out.println("name: " + name);
		return ResponseEntity.ok(name);
	}

}
