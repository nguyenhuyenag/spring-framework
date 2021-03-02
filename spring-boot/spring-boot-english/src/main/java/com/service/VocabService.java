package com.service;

import com.entity.Vocabulary;

public interface VocabService {

	Vocabulary getRandomVocab(String flag);
	
	void increaseCountById(Vocabulary vocab);

}
