package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;

@Controller
@RequestMapping("user")
public class BasicController {

	@Autowired
	private UserService service;

	@Autowired
	private UserRepository repository;

	@GetMapping("init")
	private ResponseEntity<String> init() {
		String mess = "Insert " + service.init() + " record";
		return new ResponseEntity<>(mess, HttpStatus.CREATED);
	}

	@ResponseBody
	@GetMapping("get-all-users")
	private List<User> getAll() {
		return repository.findAll();
	}

}
