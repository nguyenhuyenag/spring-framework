package com.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.service.FileService;

@Controller
@RequestMapping("api/files")
public class FileController {

	@Autowired
	private FileService fileService;

	@GetMapping("read-text")
	private ResponseEntity<List<String>> readText() throws IOException {
		List<String> content = fileService.readFile();
		return new ResponseEntity<List<String>>(content, HttpStatus.OK);
	}

}
