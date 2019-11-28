package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.util.DateTimeUtils;

@RestController
@RequestMapping("api/public")
public class PublicController {

	@GetMapping("now")
	private ResponseEntity<String> now() {
		String now = DateTimeUtils.getNow();
		return new ResponseEntity<>(now, HttpStatus.OK);
	}

}
