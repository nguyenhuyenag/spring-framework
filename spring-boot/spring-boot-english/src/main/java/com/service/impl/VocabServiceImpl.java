package com.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.VocabService;

@Service
public class VocabServiceImpl implements VocabService {
	
	@Autowired
	private VocabRepository repository;

	private final int N = 4;

	// private static final int SIZE = 20;

	private Set<String> ignoreWords = new HashSet<>();

	/**
	 * Random ngẫu nhiên trong List: [min, max + 1]
	 */
	private Vocabulary randomFromList(List<Vocabulary> list) {
		int index = ThreadLocalRandom.current().nextInt(0, (list.size() - 1) + 1);
		return list.get(index);
	}

	/**
	 * Kiểm tra không trùng với từ phía trước và tăng biến count
	 */
	private Vocabulary handle(Vocabulary vocab) {
		String word = vocab.getWord();
		if (!ignoreWords.contains(word)) { // check exits
			ignoreWords.add(word);
			if (StringUtils.isNotEmpty(vocab.getPronounce())) {
				increaseCountById(vocab); // increate count
				return vocab;
			}
		}
		return null;
	}

	@Override
	public Vocabulary getRandomVocab(String flag) {
		while (true) {
			Vocabulary vocab = repository.getRandomWord();
			if (vocab != null) {
				vocab = handle(vocab);
				if (vocab != null) {
					return vocab;
				}
			}
		}
	}

	@Override
	public Vocabulary getRandomVocab2() {
		while (true) {
			Vocabulary vocab = repository.getRandomWord();
			List<Vocabulary> list = repository.getListVocabLimitByCount(vocab.getCount(), N);
			list.add(vocab);
			vocab = randomFromList(list);
			vocab = handle(vocab);
			if (vocab != null) {
				return vocab;
			}
		}
	}

	@Transactional
	@Override
	public void increaseCountById(Vocabulary vocab) {
		vocab.setCount(vocab.getCount() + 1);
		repository.save(vocab);
	}

	@Override
	public List<String> incomplete() {
		return repository.incomplete();
	}

	@Override
	public Vocabulary search(String word) {
		Vocabulary v = repository.findByWord(word);
		// Vocabulary v = repository.advanceSearchByWord(word);
		return v != null ? v : null;
	}

	@Override
	public List<Vocabulary> incompleteVocabulary() {
		return repository.incompleteVocabulary();
	}

	@Override
	public List<Vocabulary> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.ASC, "word"));
	}

	@Override
	public Page<Vocabulary> pagination(int page, int pageSize) {
		PageRequest pageRequest = PageRequest.of(Math.max(0, page - 1), pageSize);
		Page<Vocabulary> listPage = repository.findAll(pageRequest);
		return listPage;
	}

	@Override
	public void deleteByWord(String word) {
		repository.deleteByWord(word);
	}

	@Override
	public Vocabulary update(Vocabulary v) {
		Vocabulary entity = repository.findByWord(v.getWord());
		entity.setPronounce(v.getPronounce());
		entity.setTranslate(v.getTranslate());
		return repository.save(entity);
	}

	@Override
	public String pluralNoun(String noun) {
		StringBuilder sb = new StringBuilder(noun);

		// special 1
		List<String> special1 = Arrays.asList("sheep", "aircraft", "deer", "moose", "fish", "dozen", "hundred");
		if (special1.contains(noun.toLowerCase())) {
			return noun;
		}

		// irregular nouns
		Map<String, String> map = new HashMap<>();
		map.put("man", "men");
		map.put("woman", "women");
		map.put("person", "people");
		map.put("child", "children");
		map.put("mouse", "mice");
		map.put("foot", "feet");
		map.put("tooth", "teeth");
		map.put("brother", "brethren");
		map.put("thesis", "theses");
		map.put("die", "dice");
		map.put("focus", "foci");
		map.put("ox", "oxen");
		map.put("cactus", "cacti");
		map.put("goose", "geese");
		map.put("fungus", "fungi");
		map.put("nucleus", "nuclei");
		map.put("louse", "lice");
		map.put("syllabus", "syllabi/syllabuses");
		map.put("analysis", "analyses");
		map.put("datum", "data");
		if (map.get(noun.toLowerCase()) != null) {
			return map.get(noun.toLowerCase());
		}
		
		// rule 1
		String[] rule1 = { "sh", "ch", "x", "z", "s" };
		for (String s : rule1) {
			if (noun.endsWith(s)) {
				return noun + "es";
			}
		}

		// rule 2: vowel + 'y'
		// vowel = "aeoui";
		if (noun.endsWith("y")) {
			// (2.1)
			String[] rule2 = { "ay", "ey", "oy", "uy", "iy" };
			for (String s : rule2) {
				if (noun.endsWith(s)) {
					return noun + "s";
				}
			}
			// (2.2): change 'y' -> 'i' and + es
			sb.setCharAt(sb.length() - 1, 'i');
			return sb.toString() + "es";
		}

		// rule 3: 'f' & 'fe'
		if (noun.endsWith("f")) {
			sb.deleteCharAt(sb.length() - 1);
			return sb.toString() + "ves";
		}
		if (noun.endsWith("fe")) {
			sb.delete(sb.length() - 2, sb.length());
			return sb.toString() + "ves";
		}

		// rule 4
		if (noun.endsWith("o")) {
			// vowel + 'o'
			String[] rule2 = { "ao", "eo", "oo", "uo", "io" };
			for (String s : rule2) {
				if (noun.endsWith(s)) {
					return noun + "s";
				}
			}
			//
			return noun + "es";
		}

		return noun + "s";
	}

	@Override
	public Page<Vocabulary> searchByWord(String word, int page) {
		Pageable pageable = PageRequest.of(page, 5);
		return repository.searchByWord(word, pageable);
	}

}
