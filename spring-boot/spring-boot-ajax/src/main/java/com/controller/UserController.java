package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.User;
import com.service.UserService;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model) {
		List<User> list = userService.getListUser();
		model.addAttribute("listUser", list);
		return "user";
	}

	@PutMapping("add")
	public ResponseEntity<?> add(@RequestBody User user, Model model) {
		List<User> list = userService.getListUser();
		list.add(user);
		model.addAttribute("listUser", list);
		return ResponseEntity.ok(list);
	}

	@DeleteMapping("delete")
	public ResponseEntity<?> delete(@RequestParam String email, Model model) {
		List<User> list = userService.getListUser();
		list.removeIf(t -> t.getEmail().equalsIgnoreCase(email));
		model.addAttribute("listUser", list);
		return ResponseEntity.ok(list);
	}

}
