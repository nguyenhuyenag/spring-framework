package com.controller;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.LoginRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.util.JsonUtils;

@Controller
@RequestMapping("json")
public class JsonController {

	@GetMapping("test")
	private ResponseEntity<?> basic(int choose) {
		String jsonString = "{ \"username\": \"dev1\", \"password\": \"123456\" }";
		if (choose == 1) {
			LoginRequest login = JsonUtils.toObject(jsonString, LoginRequest.class);
			return ResponseEntity.ok(login);
		}
		if (choose == 2) {
			JsonNode jsonNode = JsonUtils.toJsonNode(jsonString);
			return ResponseEntity.ok(jsonNode);
		}
		if (choose == 3) {
			JSONObject jsonObject = JsonUtils.toJSONObject(jsonString);
			return ResponseEntity.ok(jsonObject);
		}
		return ResponseEntity.ok(jsonString);
	}

}
