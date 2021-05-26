package com.service;

import java.util.List;

import com.entity.Vocabulary;

public interface VocabService {

	Vocabulary getRandomVocab(String flag);

	Vocabulary getRandomVocab2(String flag);

	void increaseCountById(Vocabulary vocab);
	
	List<String> incomplete();
	
	Vocabulary search(String word);

	List<Vocabulary> incompleteVocabulary();

}
