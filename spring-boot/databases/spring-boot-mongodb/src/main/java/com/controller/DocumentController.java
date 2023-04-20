package com.controller;

import java.util.Arrays;
import java.util.List;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Vocabulary;
import com.service.DocumentService;

@RestController
@RequestMapping("document")
public class DocumentController {

	@Autowired
	private DocumentService service;

	/**
	 * Cach (2): @RequestBody Document jsonString
	 */
	@PostMapping("insert-any")
	public ResponseEntity<?> insertAny(@RequestBody String jsonString) {
		Document d = service.insertAny(jsonString);
		if (d != null) {
			return ResponseEntity.ok(d);
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		return ResponseEntity.ok(Arrays.asList(jsonObject.get("word") + " already existed!"));
	}

	@GetMapping("basic-query")
	public ResponseEntity<?> basicQuery() {
		List<Vocabulary> list = service.basicQuery();
		return ResponseEntity.ok(list);
	}

	@GetMapping("bson-filter")
	public ResponseEntity<?> bsonFilter() {
		List<Document> list = service.bsonFilter();
		return ResponseEntity.ok(list);
	}

	@GetMapping("bson-sort")
	public ResponseEntity<?> bsonSort() {
		List<Document> list = service.bsonSort();
		return ResponseEntity.ok(list);
	}

}
