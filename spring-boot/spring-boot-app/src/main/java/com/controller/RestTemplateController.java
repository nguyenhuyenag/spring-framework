package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.Service;

@RestController
@RequestMapping("test")
public class RestTemplateController {

	@Autowired
	Service service;

	@GetMapping("get-json")
	private ResponseEntity<String> getJson() {
		String json = service.getJSON(); 
		return new ResponseEntity<>(json, HttpStatus.OK);
	}

}
