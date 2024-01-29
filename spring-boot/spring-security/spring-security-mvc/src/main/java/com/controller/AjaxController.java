package com.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class AjaxController {

	private ObjectMapper mapper = new ObjectMapper();

	@GetMapping("/ajax")
	public String ajaxView(Model model) throws JsonProcessingException {
		Map<String, String> data = new LinkedHashMap<>();
		data.put("name", "Java");
		data.put("year", "1995");
		model.addAttribute("dataList", data); // test
		model.addAttribute("dataListJSON", mapper.writeValueAsString(data));
		return "ajax";
	}

	/**
	 * POST -> Need config ajaxSetup.beforeSend in $.ajax
	 * 
	 * Biến 'errors' phải đặt ở cuối cùng?
	 */
	@ResponseBody
	@PostMapping("/api/my-ajax")
	public ResponseEntity<?> ajaxPost(@RequestBody Map<String, String> bodyData, //
			@RequestParam String name, Errors errors) {
		// If error, just return a 400 bad request, along with the error message
		if (errors.hasErrors()) {
			List<ObjectError> error = errors.getAllErrors();
			System.out.println(error);
		}
		System.out.println("RequestParam: " + name);
		System.out.println("RequestBody: " + bodyData);
		return ResponseEntity.ok("AjaxController_Success_OK_1");
	}
	
	@ResponseBody
	@PostMapping("/api/my-ajax2")
	public ResponseEntity<?> ajaxPost2(@RequestBody Map<String, String> bodyData) {
		System.out.println("RequestBody: " + bodyData);
		return ResponseEntity.ok("AjaxController_Success_OK_2");
	}

}
