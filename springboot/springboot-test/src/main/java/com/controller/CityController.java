package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.City;

@Controller
@RequestMapping("api")
public class CityController {

	@GetMapping("load-all")
	private ResponseEntity<List<City>> loadAll() {
		List<City> list = null;
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
