package com.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController {

//	@Autowired
//	UserRepository userRepository;

	@GetMapping("/ajax")
	public String ajaxView() {
		return "ajax";
	}

	/**
	 * POST -> Need config ajaxSetup.beforeSend in $.ajax
	 */
	@ResponseBody
	@PostMapping("/api/my-ajax")
	public ResponseEntity<?> ajaxPost(@RequestBody Map<String, String> map, String name, Errors errors) {
		// If error, just return a 400 bad request, along with the error message
//		if (errors.hasErrors()) {
//			String error = errors.getAllErrors().stream() //
//					.map(x -> x.getDefaultMessage()) //
//					.collect(Collectors.joining(","));
//			return ResponseEntity.badRequest().body(error);
//		}
		map.put("name", name);
		return ResponseEntity.ok(map);
	}

}
