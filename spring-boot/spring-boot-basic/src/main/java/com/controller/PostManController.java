package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostManController {

	/*-
	 * Body form-data 
	 * 
	 * 		my_array 	1 
	 * 		my_array 	2
	 * 
	 */
	@RequestMapping(value = "sending_array")
	private ResponseEntity<?> test(String[] my_array) {
		return ResponseEntity.ok(my_array);
	}

}
