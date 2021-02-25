package com.service;

import com.entity.Vocabulary;

public interface VocabService {

	int findMinId();

	int findMaxId();

	Vocabulary getVocabById(int id);

	Vocabulary getRandomVocab(String flag);
	
	void increaseCountById(Vocabulary vocab);

}
