package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.QuanLyTaiKhoanService;

@RestController
@RequestMapping("public")
public class TestController {

	@Autowired
	private QuanLyTaiKhoanService service;

	@GetMapping("get-all-user")
	public ResponseEntity<List<User>> getAll() {
		List<User> list = service.getAll();
		return ResponseEntity.ok(list);
	}

}
