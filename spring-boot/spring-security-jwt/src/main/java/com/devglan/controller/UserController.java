package com.devglan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devglan.model.User;
import com.devglan.model.UserDto;
import com.devglan.service.UserService;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

	@Autowired
	private UserService userService;

	// @Secured("ROLE_USER")
	@PreAuthorize("hasRole('USER')")
	// @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("users")
	public ResponseEntity<User> getOne(@RequestParam(value = "id") Long id) {
		return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
	}

	// @Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("get-all")
	public List<User> listUser() {
		return userService.findAll();
	}

	@PostMapping(value = "/register")
	public User saveUser(@RequestBody UserDto user) {
		return userService.save(user);
	}

}
