package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.repository.UserRepository;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private UserRepository repository;

	private static final String URL = "https://jsonplaceholder.typicode.com/todos";

	@GetMapping("get-json")
	private ResponseEntity<String> getJson() {
		RestTemplate restTemplate = new RestTemplate();
		String json = "";
		try {
			json = restTemplate.getForObject(URL, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

	@GetMapping("users")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<User> getOne(@RequestParam(value = "id") Long id) {
		Optional<User> u = repository.findById(id);
		return new ResponseEntity<>(u.get(), HttpStatus.OK);
	}

	@GetMapping("get-users")
	@PreAuthorize("hasRole('ADMIN')")
	// @Secured({"ROLE_ADMIN", "ROLE_USER"})
	public List<User> listUser() {
		return repository.findAll();
	}

}
