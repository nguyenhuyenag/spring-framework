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

    @GetMapping("/current-datetime")
    public ResponseEntity<?> getTime() {
        return ResponseEntity.ok(documentService.mongoDate());
    }

    @GetMapping("/bson-sort")
    public ResponseEntity<?> bsonSort() {
        return ResponseEntity.ok(documentService.bsonSort());
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Document request) {
        return ResponseEntity.ok(documentService.insert(request));
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

}
