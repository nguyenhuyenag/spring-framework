package com.controller;

import java.security.Principal;
import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.entity.User;
import com.repository.UserRepository;
import com.util.JsonUtils;

/*-
 *	@PreAuthorize("hasRole('ADMIN')")
 * 	@PreAuthorize("hasAnyRole('USER', 'ADMIN')") 
 * 	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
 */
@RestController
public class ApiController {

	@Autowired
	private UserRepository repository;

	private static final String URL = "https://random-data-api.com/api/v2/credit_cards";

	@GetMapping("/public/get-json")
	private ResponseEntity<?> getJson() {
		RestTemplate restTemplate = new RestTemplate();
		String json = "";
		try {
			json = restTemplate.getForObject(URL, String.class);
		} catch (RestClientException e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok(JsonUtils.toJsonNode(json));
	}

	@GetMapping("/api/who-am-i")
	// @RolesAllowed({ "USER", "ADMIN" })
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	public ResponseEntity<User> userInfo(Principal principal) {
		String username = principal.getName();
		User user = repository.findByUsername(username);
		return ResponseEntity.ok(user);
	}

	@GetMapping("/api/list-user")
	@RolesAllowed("ADMIN")
	public List<User> listUser() {
		return repository.findAll();
	}

}
