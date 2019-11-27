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
@RequestMapping("/api")
public class TaskController {

	@Autowired
	UserRepository repository;

//	@GetMapping
//	public List<User> listTasks() {
//		return repository.findAll();
//	}

	@GetMapping("load-all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> newTasks() {
		return repository.findAll();
	}

//	@PutMapping("/{taskId}")
//	public String updateTasks(@PathVariable("taskId") Integer id) {
//		return "ID: " + id;
//	}
//
//	@DeleteMapping("/{taskId}")
//	public String deleteTasks(@PathVariable("taskId") Integer id) {
//		return "ID: " + id;
//	}
}
