package com.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.dto.Photo;
import com.spring.service.JSONService;

@Service
public class JSONServiceImpl implements JSONService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Photo getJsonByRestTemplate(String url) {
		return restTemplate.getForObject(url, Photo.class);
	}

}
