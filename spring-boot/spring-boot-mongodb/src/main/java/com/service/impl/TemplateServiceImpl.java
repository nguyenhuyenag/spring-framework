package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;

@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private MongoTemplate template;
	
	// Class<Vocabulary> entityClass = Vocabulary.class;

	@Override
	public List<Vocabulary> findAll() {
		return template.findAll(Vocabulary.class);
	}

}
