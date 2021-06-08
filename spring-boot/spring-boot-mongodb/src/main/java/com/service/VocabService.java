package com.service;

import java.util.List;

import com.entity.Vocabulary;

public interface VocabService {
	
	List<Vocabulary> findAll();
	
	List<Vocabulary> findAllSortByWord();
	
}
