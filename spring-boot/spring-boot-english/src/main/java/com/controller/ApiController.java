package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("search")
	private ResponseEntity<Vocabulary> search(String word) {
		Vocabulary data = service.search(word);
		return ResponseEntity.ok(data);
	}
	
	@DeleteMapping("delete")
	private String delete(String word) {
		service.deleteByWord(word);
		return "OK";
	}

}
