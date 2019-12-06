package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.service.UserService;

@Controller
@RequestMapping("api")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("load-all")
	private ResponseEntity<List<User>> loadAll() {
		List<User> list = userService.loadAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
