package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.entity.User;
import com.request.EditUserRequest;
import com.service.QuanLyTaiKhoanService;

@Controller
public class QLTKController {

	@Autowired
	private QuanLyTaiKhoanService service;

	@GetMapping("quanlytaikhoan")
	public String qltk() {
		return "quanlytaikhoan";
	}

	@GetMapping("get-all-user")
	public ResponseEntity<List<User>> getAll() {
		List<User> list = service.getAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping("change-user-status")
	public ResponseEntity<?> changeUserSatus(String email) {
		boolean flag = service.changeUserStatus(email);
		return ResponseEntity.ok(flag);
	}
	
	@PostMapping("edit-user")
	public ResponseEntity<?> editUser(@RequestBody EditUserRequest request) {
		boolean flag = service.editUser(request);
		return ResponseEntity.ok(flag);
	}

}
