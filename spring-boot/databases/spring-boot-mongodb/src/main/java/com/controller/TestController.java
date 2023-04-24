package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.MongodbTransaction;

@RestController
public class TestController {

	@Autowired
	MongodbTransaction mongodbTransaction;

	@GetMapping("test-transaction")
	public ResponseEntity<?> testTransaction() {
		mongodbTransaction.testTransaction();
		return ResponseEntity.ok(null);
	}

}
