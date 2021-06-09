package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.request.InsertDTO;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {

	@Autowired
	private VocabRepository repository;

	@Override
	public Vocabulary insert(InsertDTO dto) {
		Optional<Vocabulary> opt = repository.findByWord(dto.getWord().toLowerCase());
		if (opt.isPresent()) {
			return null;
		}
		Vocabulary entity = new Vocabulary(dto.getWord(), dto.getPronounce(), dto.getTranslate());
		return repository.insert(entity);
		// return repository.save(entity);
	}

	@Override
	public Vocabulary update(InsertDTO dto) {
		Optional<Vocabulary> opt = repository.findByWord(dto.getWord().toLowerCase());
		if (!opt.isPresent()) {
			return null;
		}
		Vocabulary entity = opt.get();
		entity.setPronounce(dto.getPronounce());
		entity.setTranslate(dto.getTranslate());
		return repository.save(entity);
	}

	@Override
	public List<Vocabulary> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Vocabulary> findAllSortByWord() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "word"));
	}

}
