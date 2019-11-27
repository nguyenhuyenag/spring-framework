package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.User;
import com.request.RegisterRequest;
import com.service.UserService;

@Controller
@RequestMapping("api/admin")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("load-all")
	@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<List<User>> loadAll() {
		List<User> list = userService.loadAll();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}

	@PostMapping("register")
	private ResponseEntity<Void> register(@RequestBody RegisterRequest dto) {
		userService.register(dto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
