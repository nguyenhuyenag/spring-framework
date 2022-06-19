package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Jokes;
import com.repository.JokesRepository;

@RestController
// @RequestMapping("public")
public class JokesController {

	@Autowired
	private JokesRepository repository;

	@GetMapping("v1/get-one")
	private ResponseEntity<?> getOne() {
		Jokes joke = repository.getOne();
		return ResponseEntity.ok(joke);
	}
	
	@GetMapping("api/get-one")
	private ResponseEntity<?> getJson() {
		Jokes joke = repository.getOne();
		return ResponseEntity.ok(joke);
	}

//	@GetMapping("user-info")
//	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//	// @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//	public ResponseEntity<User> userInfo(Principal principal) {
//		String username = principal.getName();
//		Optional<User> opt = repository.findByUsername(username);
//		User user = opt.get();
//		user.setPassword(null);
//		user.setRoles(null);
//		return new ResponseEntity<>(user, HttpStatus.OK);
//	}
//
//	@GetMapping("get-all-user")
//	@PreAuthorize("hasRole('ADMIN')")
//	public List<User> listUser() {
//		return repository.findAll();
//	}

}
