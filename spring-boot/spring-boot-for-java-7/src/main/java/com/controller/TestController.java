package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Test;
import com.repository.TestRepository;

@RestController
public class TestController {

	@Autowired
	private TestRepository repository;

	@RequestMapping("test")
	private ResponseEntity<?> test() {
		List<Test> findAll = repository.findAll();
		return ResponseEntity.ok(findAll);
	}

}
