package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Service {

	private static final String URL = "https://jsonplaceholder.typicode.com/todos?id=1";

	@Autowired
	RestTemplate restTemplate;

	public String getJSON() {
		String json = "";
		try {
			json = restTemplate.getForObject(URL, String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

}
