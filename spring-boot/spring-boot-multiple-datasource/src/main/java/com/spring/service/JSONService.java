package com.spring.service;

import com.spring.dto.Photo;

public interface JSONService {
	
	Photo getJsonByRestTemplate(String url);
	
}
