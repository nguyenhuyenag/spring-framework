package com.service;

import java.util.List;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface VocabService {
	
	Vocabulary insert(InsertDTO dto);
	
	Vocabulary update(InsertDTO dto);
	
	List<Vocabulary> findAll();
	
	List<Vocabulary> findAllSortByWord();
	
	List<Vocabulary> findByCountBetween(int from, int to);
	
	// JSON
	Vocabulary findByWordUsingJSON(String word);
	
}
