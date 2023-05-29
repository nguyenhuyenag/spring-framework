package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
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
	@GetMapping("/api/ajax")
	public ResponseEntity<?> ajaxPost() {
		List<User> findAll = userRepository.findAll();
		System.out.println(findAll.toString());
		return ResponseEntity.ok(findAll);
	}

}
