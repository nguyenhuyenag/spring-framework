package com.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dto.CategoryResDto;
import com.entity.Category;
import com.service.MapStructService;

@Service
public class MapStructServiceTest {
	
	@Autowired
	MapStructService mapStructService;
	
	public void convert() {
		CategoryResDto dto = mapStructService.categoryToCategoryDto(init());
		System.out.println(dto.toString());
	}
	
	public Category init() {
		Category c = new Category();
		c.setId(1412L);
		c.setName("John");
		c.setStatusCategory(true);
		c.setCreateDate("20/06/2023");
		c.setUpdateTime(LocalDateTime.now());
		c.setLabel("ASC+");
		return c;
	}
	
}
