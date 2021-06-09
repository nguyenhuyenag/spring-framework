package com.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
