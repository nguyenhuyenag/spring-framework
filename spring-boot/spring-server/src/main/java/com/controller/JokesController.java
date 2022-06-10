package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Jokes;
import com.service.JokesService;

@RestController
@RequestMapping("api")
public class JokesController {
	
	@Autowired
	private JokesService jokesService;

	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("get-jokes")
	private ResponseEntity<?> getJkes() {
		Jokes jokes = jokesService.getOne();
		return ResponseEntity.ok(jokes);
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
