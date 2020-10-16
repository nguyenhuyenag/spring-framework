package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.repository.UserRepository;
import com.response.ApiResponse;
import com.util.DateTimeUtils;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserRepository repository;

	// private static final String URL = "https://jsonplaceholder.typicode.com/todos";

	@GetMapping("public/timestamp")
	private ResponseEntity<ApiResponse> now() {
		String time = DateTimeUtils.getNow();
		ApiResponse api = new ApiResponse("OK_200", "Xử lý dữ liệu thành công", time);
		return new ResponseEntity<>(api, HttpStatus.OK);
	}

	@GetMapping("get-json")
	@PreAuthorize("hasRole('USER')")
	private ResponseEntity<ApiResponse> getJson() {
		// String json = restTemplate.getForObject(URL, String.class);
//		Optional<User> u = repository.findById(5L);
//		if (u.isPresent()) {
//			return new ResponseEntity<>(api, HttpStatus.OK);
//		}
		ApiResponse api = new ApiResponse("OK_200", "Xử lý dữ liệu thành công", null);
		return new ResponseEntity<>(api, HttpStatus.OK);
	}

	@GetMapping("get-users")
	@PreAuthorize("hasRole('ADMIN')")
	private ResponseEntity<List<User>> getUsers() {
		List<User> list = repository.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
