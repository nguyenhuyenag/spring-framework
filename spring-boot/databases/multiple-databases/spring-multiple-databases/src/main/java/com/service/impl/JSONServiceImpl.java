package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dto.Photo;
import com.service.JSONService;

@Service
public class JSONServiceImpl implements JSONService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Photo getJsonByRestTemplate(String url) {
		return restTemplate.getForObject(url, Photo.class);
	}

}
