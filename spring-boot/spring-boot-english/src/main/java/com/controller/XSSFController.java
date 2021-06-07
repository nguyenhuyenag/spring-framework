package com.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.XSSFService;

@RestController
@RequestMapping("xssf")
public class XSSFController {

	@Autowired
	XSSFService xssfService;

	@GetMapping("import-data")
	private ResponseEntity<?> importExcel() {
		List<String> list = xssfService.importExcel();
		return ResponseEntity.ok(list);
	}

	@GetMapping("add-new")
	private ResponseEntity<?> addNew() {
		int count = xssfService.addNew();
		String data = "Add new " + count + " vocabulary";
		return ResponseEntity.ok(Arrays.asList(data));
	}

}
