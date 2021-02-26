package com.service;

import com.entity.Vocabulary;

public interface VocabService {

	//Vocabulary getVocabById(int id);

	Vocabulary getRandomVocab(String flag);
	
	void increaseCountById(Vocabulary vocab);

}
