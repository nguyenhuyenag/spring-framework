package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.request.InsertDTO;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {

	private static final Log log = LogFactory.getLog(VocabServiceImpl.class);

	@Autowired
	private VocabRepository repository;

	@Override
	public Vocabulary insert(InsertDTO dto) {
		Optional<Vocabulary> opt = repository.findByWord(dto.getWord().toLowerCase());
		if (opt.isPresent()) {
			return null;
		}
		Vocabulary entity = new Vocabulary(dto.getWord(), dto.getPronounce(), dto.getTranslate());
		entity = repository.insert(entity); // repository.save(entity);
		log.info("Insert: " + dto.getWord());
		return entity;
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
	public List<Vocabulary> findAllAndSort() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "word"));
	}

	@Override
	public Vocabulary findByWordUsingJSON(String word) {
		Optional<Vocabulary> opt = repository.findByWord(word.toLowerCase());
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public List<Vocabulary> findByCountBetween(int from, int to) {
		return repository.findByCountBetween(from, to);
	}

	@Override
	public Vocabulary findByWord(String word) {
		Optional<Vocabulary> opt = repository.findByWord(word);
		return opt.isPresent() ? opt.get() : null;
	}

	@Override
	public boolean deleteByWord(String word) {
		Optional<Vocabulary> opt = repository.findByWord(word);
		if (opt.isPresent()) {
			repository.delete(opt.get());
			return true;
		}
		return false;
	}

	@Override
	public List<Vocabulary> findBetweenByJSON(int from, int to) {
		return repository.findBetweenByJSON(from, to);
	}

	@Override
	public List<Vocabulary> findWithORConditons(String startWith1, String startWith2) {
		return repository.findWithORConditons(startWith1, startWith2);
	}

	@Override
	public List<Vocabulary> findWithRegex(String regex) {
		return repository.findWithRegex(regex);
	}

	@Override
	public List<Vocabulary> findAndSortByJSON() {
		return repository.findAndSortByJSON();
	}

}
