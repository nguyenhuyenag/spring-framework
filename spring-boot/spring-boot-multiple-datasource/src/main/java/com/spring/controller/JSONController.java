package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.dto.Photo;
import com.spring.service.JSONService;

@Controller
@RequestMapping("api/json")
public class JSONController {

	@Autowired
	private JSONService jsonService;

	@PostMapping("get-json-data")
	private ResponseEntity<Photo> getJson(@RequestBody String url) {
		Photo photo = jsonService.getJsonByRestTemplate(url);
		return new ResponseEntity<Photo>(photo, HttpStatus.OK);
	}

}
