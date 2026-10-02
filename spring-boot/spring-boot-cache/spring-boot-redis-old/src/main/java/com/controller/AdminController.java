package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserRepository repository;

	@GetMapping("get-all-user")
	@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<List<User>> get() {
		List<User> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
