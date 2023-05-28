package com.multitenant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multitenant.domain.Office;
import com.multitenant.repository.OfficeRepository;

@RestController
public class OfficeController {

	@Autowired
	private OfficeRepository officeRepository;

	@PostMapping(path = "/offices")
	public ResponseEntity<?> createEmployee() {
		List<Office> findAll = officeRepository.findAll();
		return ResponseEntity.ok(findAll);
	}
}
