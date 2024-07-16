package com.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.json.JSONObject;
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
@RequiredArgsConstructor
public class DocumentController {

	private final DocumentService documentService;

	@GetMapping("current-datetime")
	public ResponseEntity<?> getTime() {
		return ResponseEntity.ok(documentService.mongoDate());
	}

	/**
	 * Cach (2): @RequestBody Document jsonString
	 */
	@PostMapping("insert-any")
	public ResponseEntity<?> insertAny(@RequestBody String jsonString) {
		Document d = documentService.insertAny(jsonString);
		if (d != null) {
			return ResponseEntity.ok(d);
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		return ResponseEntity.ok(List.of(jsonObject.get("word") + " already existed!"));
	}

	@GetMapping("basic-query")
	public ResponseEntity<?> basicQuery() {
		List<Vocabulary> list = documentService.basicQuery();
		return ResponseEntity.ok(list);
	}

	@GetMapping("bson-filter")
	public ResponseEntity<?> bsonFilter() {
		return ResponseEntity.ok(documentService.bsonFilter());
	}

	@GetMapping("bson-sort")
	public ResponseEntity<?> bsonSort() {
		List<Document> list = documentService.bsonSort();
		return ResponseEntity.ok(list);
	}

}
