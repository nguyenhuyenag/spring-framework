package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.MultiTenantManager;
import com.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository repository;

	@GetMapping("user")
	private ResponseEntity<?> user(String databasename, String username, String password) {
		MultiTenantManager.setTenant(databasename, username, password);
		MultiTenantManager.switchTenant(databasename);
		return ResponseEntity.ok(repository.findAll());
	}

}
