package com.multitenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.multitenant.domain.Office;
import com.multitenant.repository.OfficeRepository;

@Controller
public class IndexController {

	@Autowired
	private OfficeRepository officeRepository;

	@GetMapping("index")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("/offices")
	public ResponseEntity<?> offices() {
		List<Office> findAll = officeRepository.findAll();
		return ResponseEntity.ok(findAll);
	}

}
