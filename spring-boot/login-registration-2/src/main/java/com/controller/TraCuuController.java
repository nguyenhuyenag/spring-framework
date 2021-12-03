package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.TTCN;
import com.service.TraCuuService;

@Controller
public class TraCuuController {

	@Autowired
	private TraCuuService service;

	@GetMapping("tracuu")
	public String tracuu() {
		return "tracuu";
	}

	@PostMapping("tracuu")
	public ResponseEntity<List<TTCN>> tracuu(String masobhxh, String hoten, String diachilh) {
		List<TTCN> list = service.tracuu(masobhxh, hoten, diachilh);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
