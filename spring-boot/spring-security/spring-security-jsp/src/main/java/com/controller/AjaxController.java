package com.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/**
	 * GET 	-> OK
	 * POST -> Need config ajaxSetup.beforeSend in $.ajax
	 */
	@ResponseBody
	@PostMapping("/api/my-ajax")
	public ResponseEntity<?> ajaxPost(@RequestBody Map<String, String> map, String name) {
		map.put("name", name);
		return ResponseEntity.ok(map);
	}

}
