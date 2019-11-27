package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("api")
public class TaskController {

	@Autowired
	UserRepository repository;

	@GetMapping("load-all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> newTasks() {
		return repository.findAll();
	}

}
