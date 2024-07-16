package com.controller;

import com.service.CriteriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("criteria")
@RequiredArgsConstructor
public class CriteriaController {

    private final CriteriaService criteriaService;

    @GetMapping("/find-lte")
    public ResponseEntity<?> findLte() {
        return ResponseEntity.ok(criteriaService.findLte());
    }

    @GetMapping("/find-regex")
    public ResponseEntity<?> findRegex() {
        return ResponseEntity.ok(criteriaService.findRegex());
    }

}
