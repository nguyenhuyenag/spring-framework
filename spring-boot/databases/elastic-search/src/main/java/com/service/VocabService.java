package com.service;

import java.util.List;

import com.entity.Vocabulary;
import com.request.InsertDTO;

public interface VocabService {

	Vocabulary insert(InsertDTO dto);

	Vocabulary update(InsertDTO dto);

	List<Vocabulary> findAll();

	Vocabulary findByWord(String word);

	boolean deleteByWord(String word);

	List<Vocabulary> findAllAndSort();

	List<Vocabulary> findByCountBetween(int from, int to);

	// Regex
	List<Vocabulary> findWithRegex(String regex);

	// JSON
	Vocabulary findByWordUsingJSON(String word);

	List<Vocabulary> findBetweenByJSON(int from, int to);
	
	List<Vocabulary> findAndSortByJSON();

	List<Vocabulary> findWithORConditons(String startWith1, String startWith2);

}
