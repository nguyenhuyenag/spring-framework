package com.service.impl;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {
	
	private int cid = 1;

	@Autowired
	private VocabRepository repository;

	@Override
	public int findMinId() {
		Integer min = repository.findMinId();
		return min != null ? min : 0;
	}

	@Override
	public int findMaxId() {
		Integer max = repository.findMaxId();
		return max != null ? max : 0;
	}

	@Override
	public Vocabulary getVocabById(int id) {
		Optional<Vocabulary> opt = repository.findById(id);
		if (opt.isPresent()) {
			Vocabulary vocab = opt.get();
			// first char uppercase
			vocab.setMean(StringUtils.capitalize(vocab.getMean()));
			return vocab;
		}
		return null;
	}
	
	// Sinh ngẫu nhiên 1 số trong đoạn [min, max] ngoại trừ số t
	private int randomExcept(int t) {
		int min = findMinId(), max = findMaxId();
		while (true) {
			int id = ThreadLocalRandom.current().nextInt(min, max + 1);
			if (id != t) {
				return id;
			}
		}
	}

	@Override
	public Vocabulary getRandomVocab() {
		while (true) {
			int id = randomExcept(cid);
			cid = id;
			Vocabulary dict = getVocabById(id);
			if (dict != null) {
				return dict;
			}
		}
	}

}
