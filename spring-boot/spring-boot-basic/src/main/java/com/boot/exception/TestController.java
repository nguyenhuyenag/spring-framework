package com.boot.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserService;

@RestController
@RequestMapping("user")
public class TestController {

	@Autowired
	private UserService service;

	@GetMapping("search")
	private ResponseEntity<?> getById(@RequestParam("id") Integer id) {
		User user = service.getById(id);
		return ResponseEntity.ok(user);
	}

}
