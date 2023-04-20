package com.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {

	@Autowired
	private VocabRepository repository;

	@Autowired
	private EntityManager entityManager;

	@Override
	public void CollectionParam() {
		List<String> words = Arrays.asList("bake", "concept", "theater");
		List<Vocabulary> list = repository.findVocabByWordList(words);
		list.forEach(System.out::println);
	}

	@Override
	public void findUserByEmails() {
		Set<String> emails = new HashSet<>(Arrays.asList("ac", "an", "ar"));
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Vocabulary> query = cb.createQuery(Vocabulary.class);
		Root<Vocabulary> user = query.from(Vocabulary.class);
		Path<String> emailPath = user.get("word");
		List<Predicate> predicates = new ArrayList<>();
		for (String email : emails) {
			predicates.add(cb.like(emailPath, email));
		}
		query.select(user).where(cb.or(predicates.toArray(new Predicate[predicates.size()])));
		List<Vocabulary> list = entityManager.createQuery(query).getResultList();
		list.forEach(t -> System.out.println(t));
	}

}
