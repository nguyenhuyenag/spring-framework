package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.service.VocabService;

@RestController
public class VocabController {

	@Autowired
	private VocabService service;

	@GetMapping("random-vocab")
	private ResponseEntity<Vocabulary> getRandomVocab() {
		Vocabulary v = service.getRandomVocab2();
		return ResponseEntity.ok(v);
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

	@PostMapping("update")
	private ResponseEntity<Vocabulary> update(@RequestBody Vocabulary v) {
		Vocabulary data = service.update(v);
		return ResponseEntity.ok(data);
	}

}
