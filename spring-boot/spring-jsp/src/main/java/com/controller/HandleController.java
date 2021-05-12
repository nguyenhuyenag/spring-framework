package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HandleController {

	@GetMapping("request")
	public String request(HttpServletRequest req) {
		//Map<String, String[]> map = req.getParameterMap();
		//map.forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));
		return "request";
	}

}
