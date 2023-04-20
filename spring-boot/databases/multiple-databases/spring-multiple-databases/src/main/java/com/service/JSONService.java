package com.service;

import com.dto.Photo;

public interface JSONService {
	
	Photo getJsonByRestTemplate(String url);
	
}
