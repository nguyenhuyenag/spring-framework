//package com.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.entity.User;
//import com.repository.UserRepository;
//
//@RestController
//@RequestMapping("rest-api")
//public class RestApiController {
//
//	@Autowired
//	UserRepository userRepository;
//
//	@GetMapping("users")
//	public ResponseEntity<List<?>> users() {
//		List<User> listUsers = userRepository.findAll();
//		return ResponseEntity.ok(listUsers);
//	}
//
//}
