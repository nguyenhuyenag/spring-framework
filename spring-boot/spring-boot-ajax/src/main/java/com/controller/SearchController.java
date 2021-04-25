package com.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.model.AjaxResponseBody;
import com.model.SearchCriteria;
import com.model.User;
import com.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@GetMapping("search")
	public String index() {
		return "search";
	}

	@PostMapping("/api/search")
	public ResponseEntity<?> getSearchResultViaAjax(@RequestBody SearchCriteria search, Errors errors) {
		AjaxResponseBody result = new AjaxResponseBody();
		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			result.setMsg( //
				errors.getAllErrors().stream() //
				.map(x -> x.getDefaultMessage()) //
				.collect(Collectors.joining(",")) //
			);
			return ResponseEntity.badRequest().body(result);
		}
		List<User> users = searchService.findByUserNameOrEmail(search.getUsername());
		if (users.isEmpty()) {
			result.setMsg("no user found!");
		} else {
			result.setMsg("success");
		}
		result.setResult(users);
		return ResponseEntity.ok(result);
	}

}
