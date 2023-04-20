package com.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.FileService;
import com.util.FileUtils;
import com.util.JsonUtils;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	VocabRepository repository;

	@Override
	public void importData() {
		try {
			Resource resource = new ClassPathResource("data/english.json");
			if (!resource.exists()) {
				System.out.println("File not found!");
				return;
			}
			String content = FileUtils.readFile(resource.getFile().toPath());
			List<Vocabulary> list = JsonUtils.toList(content, Vocabulary.class);
			Predicate<Vocabulary> predicate = t -> !repository.findByWord(t.getWord().toLowerCase()).isPresent();
			List<Vocabulary> entities = list.stream().filter(predicate).collect(Collectors.toList());
			repository.saveAll(entities);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
