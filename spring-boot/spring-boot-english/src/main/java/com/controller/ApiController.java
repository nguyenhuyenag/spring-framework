package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.service.VocabService;
import com.service.XSSFService;

@RestController
public class ApiController {

	@Autowired
	XSSFService xssfService;

	@Autowired
	private VocabService service;

	@GetMapping("import-data")
	private ResponseEntity<?> importExcel() {
		List<String> list = xssfService.importExcel();
		return ResponseEntity.ok(list);
	}

	@GetMapping("random-vocab")
	private ResponseEntity<Vocabulary> getRandomVocab(String flag) {
		Vocabulary dict = service.getRandomVocab2(flag);
		return ResponseEntity.ok(dict);
	}
	
	@GetMapping("incomplete")
	private ResponseEntity<?> incomplete() {
		List<String> data = service.incomplete();
		return ResponseEntity.ok(data);
	}
	
	@GetMapping("append")
	private ResponseEntity<?> append() {
		List<String> data = service.incomplete();
		return ResponseEntity.ok(data);
	}

}
