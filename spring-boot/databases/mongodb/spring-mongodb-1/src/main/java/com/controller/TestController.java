package com.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.MongodbTransaction;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MongodbTransaction mongodbTransaction;

    @GetMapping("test-transaction")
    public ResponseEntity<?> testTransaction() {
        mongodbTransaction.testTransaction();
        return ResponseEntity.ok(null);
    }

}
