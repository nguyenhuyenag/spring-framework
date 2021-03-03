package com.service;

import com.entity.Vocabulary;

public interface VocabService {

	Vocabulary getRandomVocab(String flag);

	Vocabulary getRandomVocab2(String flag);

	void increaseCountById(Vocabulary vocab);

}
