package com.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.request.InsertDTO;
import com.service.VocabService;

@RestController
@RequestMapping("jpa")
public class JPAController {

	@Autowired
	private VocabService service;

	private Map<String, String> map = new HashMap<>();

	@PostMapping("insert")
	public ResponseEntity<?> insert(@RequestBody InsertDTO dto) {
		Vocabulary v = service.insert(dto);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.ok(Arrays.asList(dto.getWord() + " already existed!"));
	}

	@PostMapping("update")
	public ResponseEntity<?> update(@RequestBody InsertDTO dto) {
		Vocabulary v = service.update(dto);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		return ResponseEntity.ok(Arrays.asList(dto.getWord() + " doesn't existed!"));
	}

	@GetMapping("find-by-word")
	public ResponseEntity<?> findByWord(String word) {
		Vocabulary v = service.findByWord(word);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		List<String> list = Arrays.asList(word + " doesn't existed!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
	}

	@GetMapping("delete-by-word")
	public ResponseEntity<?> deleteByWord(String word) {
		boolean b = service.deleteByWord(word);
		if (b) {
			return ResponseEntity.ok(b);
		}
		List<String> list = Arrays.asList(word + " doesn't existed!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(list);
	}

	@GetMapping("find-all")
	public ResponseEntity<?> findAll() {
		List<?> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@GetMapping("find-between")
	public ResponseEntity<?> findBetween(int from, int to) {
		List<?> list = service.findByCountBetween(from, to);
		return ResponseEntity.ok(list);
	}

	@GetMapping("find-all-and-sort")
	public ResponseEntity<?> findAllAndSort() {
		List<?> list = service.findAllAndSort();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("find-and-sort-by-json")
	public ResponseEntity<?> findAndSortJSON() {
		List<?> list = service.findAndSortByJSON();
		return ResponseEntity.ok(list);
	}

	// Regex
	@GetMapping("find-with-regex")
	public ResponseEntity<?> findWithRegex(String regex) {
		List<?> list = service.findWithRegex(regex);
		return ResponseEntity.ok(list);
	}

	// JSON
	@GetMapping("find-by-json")
	public ResponseEntity<?> findByJSON(String word) {
		Vocabulary v = service.findByWordUsingJSON(word);
		if (v != null) {
			return ResponseEntity.ok(v);
		}
		map.put("message", "Not found!");
		return ResponseEntity.ok(map);
	}

	@GetMapping("find-between-by-json")
	public ResponseEntity<?> findBetweenByJSON(int from, int to) {
		List<Vocabulary> list = service.findBetweenByJSON(from, to);
		return ResponseEntity.ok(list);
	}

	@GetMapping("find-with-or-conditons")
	public ResponseEntity<?> findWithORConditons() {
		List<Vocabulary> list = service.findWithORConditons("adjective", "budget");
		return ResponseEntity.ok(list);
	}

}
