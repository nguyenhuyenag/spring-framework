package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Jokes;
import com.service.JokesService;

@RestController
public class JokesController {

	@Autowired
	private JokesService jokesService;

	@GetMapping("v1/get-one")
	private ResponseEntity<?> getOne() {
		Jokes joke = jokesService.getOne(-1);
		return ResponseEntity.ok(joke);
	}
	
	@RequestMapping("v2/get-one") // POST or GET
	private ResponseEntity<?> getJson(@RequestParam(value = "id", defaultValue = "-1") int id) {
		Jokes joke = jokesService.getOne(id);
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
