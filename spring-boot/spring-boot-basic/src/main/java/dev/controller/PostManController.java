package dev.controller;

import java.util.Arrays;

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
	 * 		to_array	1, 2, 3
	 * 
	 */
	@RequestMapping(value = "sending_array")
	private ResponseEntity<?> test(String[] my_array, Integer[] to_array) {
		System.out.println(Arrays.toString(to_array));
		return ResponseEntity.ok(to_array);
	}

}
