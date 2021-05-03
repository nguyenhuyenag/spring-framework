package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model.User;
import com.service.UserService;

@RestController
public class PageController {

	@Autowired
	private UserService userService;

	@GetMapping("user")
	public ResponseEntity<?> get(@RequestParam(defaultValue = "1") int page, //
			@RequestParam(defaultValue = "10") int size, //
			@RequestParam(defaultValue = "id") String sortby) {
		List<User> pages = userService.get(page, size, sortby);
		return ResponseEntity.ok(pages);
	}

	@GetMapping("page-info")
	public ResponseEntity<?> info(HttpServletRequest req) {
		String page = req.getParameter("page");
		String size = req.getParameter("size");
		Map<String, Object> map = userService.info(Integer.parseInt(page), Integer.parseInt(size));
		return ResponseEntity.ok(map);
	}

}
