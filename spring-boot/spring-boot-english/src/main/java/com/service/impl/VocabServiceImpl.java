package com.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {

	@Autowired
	private VocabRepository repository;

	private Set<String> ignoreWords = new HashSet<>();

//	@Override
//	public Vocabulary getVocabById(int id) {
//		Optional<Vocabulary> opt = repository.findById(id);
//		if (opt.isPresent()) {
//			Vocabulary vocab = opt.get();
//			// first char uppercase
//			vocab.setMean(StringUtils.capitalize(vocab.getMean()));
//			return vocab;
//		}
//		return null;
//	}

	@Override
	public Vocabulary getRandomVocab(String flag) {
		while (true) {
			Vocabulary vocab = repository.getRandomWord();
			if (vocab != null) {
				String word = vocab.getWord();
				if (!ignoreWords.contains(word)) {
					ignoreWords.add(word); // add to ignore list
					if ("1".equals(flag)) {
						increaseCountById(vocab); // count++
					}
					// first char uppercase
					vocab.setMean(StringUtils.capitalize(vocab.getMean()));
					return vocab;
				}
			}
		}
	}

	@Transactional
	@Override
	public void increaseCountById(Vocabulary vocab) {
		vocab.setCount(vocab.getCount() + 1);
		repository.save(vocab);
	}

}
