package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.impl.TemplateService;

@RestController
@RequestMapping("template")
public class TemplateController {
	
	@Autowired
	private TemplateService service;
	
	@GetMapping("find-all")
	public ResponseEntity<?> findAll() {
		List<?> list = service.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
}
