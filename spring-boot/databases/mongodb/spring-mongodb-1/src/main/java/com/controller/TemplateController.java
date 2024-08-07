package com.controller;

import java.util.Arrays;
import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entity.Vocabulary;
import com.request.InsertDTO;
import com.service.TemplateService;

@RestController
@RequestMapping("/template")
@RequiredArgsConstructor
public class TemplateController {

    private final TemplateService service;

    // http://localhost:8080/template/find-all?size=10&page=0
    @GetMapping("/find-all")
    public ResponseEntity<?> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        List<?> list = service.findAllAndPageable(page, size);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/find-by-isbn")
    public ResponseEntity<?> findOne(String isbn) {
        var book = service.findByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @GetMapping("/find-and-modify")
    public ResponseEntity<?> findAndModify() {
        return ResponseEntity.ok(service.findAndModify());
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody InsertDTO dto) {
        Vocabulary v = service.insert(dto);
        if (v != null) {
            return ResponseEntity.ok(v);
        }
        return ResponseEntity.ok(List.of(dto.getWord() + " already existed!"));
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody InsertDTO dto) {
        Vocabulary v = service.update(dto);
        if (v != null) {
            return ResponseEntity.ok(v);
        }
        return ResponseEntity.ok(List.of(dto.getWord() + " doesn't existed!"));
    }

    @GetMapping("/remove")
    public ResponseEntity<?> remove(String word) {
        boolean b = service.remove(word);
        if (b) {
            ResponseEntity.ok(List.of("Delete successful"));
        }
        return ResponseEntity.ok(List.of(word + " not found!"));
    }

    @GetMapping("/find-all-and-sort")
    public ResponseEntity<?> findAllAndSort() {
        List<?> list = service.findAllAndSort();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/find-and-modify")
    public ResponseEntity<?> findAndModify(@RequestBody InsertDTO dto) {
        Vocabulary v = service.findAndModify(dto);
        return ResponseEntity.ok(v);
    }

//	// Co the dung
//	// @RequestBody Document jsonString
//	@PostMapping("insert-any")
//	public ResponseEntity<?> insertAny(@RequestBody String jsonString) {
//		Document d = service.insertAny(jsonString);
//		return ResponseEntity.ok(d);
//	}

}
