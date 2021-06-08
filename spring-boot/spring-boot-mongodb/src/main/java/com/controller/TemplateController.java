package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.VocabService;

@RestController
@RequestMapping("template")
public class TemplateController {
	
	@Autowired
	private VocabService service;
	
	@GetMapping("find-all")
	public ResponseEntity<?> findAll() {
		List<?> list = service.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("find-all-sort-by-word")
	public ResponseEntity<?> findAllSortByWord() {
		List<?> list = service.findAllSortByWord();
		return ResponseEntity.ok(list);
	}
	
}
