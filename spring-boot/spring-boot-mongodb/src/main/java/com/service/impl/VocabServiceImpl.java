package com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {
	
	@Autowired
	private VocabRepository repository;

	@Override
	public List<Vocabulary> findAll() {
		return repository.findAll().stream().limit(50).collect(Collectors.toList());
	}

	@Override
	public List<Vocabulary> findAllSortByWord() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "word").and(Sort.by("count")));
	}

}
