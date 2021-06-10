package com.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Vocabulary;
import com.repository.VocabRepository;
import com.service.FTPService;
import com.util.FilesUtils;
import com.util.JsonUtils;

@Service
public class FTPServiceImpl implements FTPService {

	@Autowired
	private VocabRepository repository;

	@Override
	public void exportJSON() {
		Path path = Paths.get("file/english.json");
		List<Vocabulary> list = repository.findAllByOrderByWord();
		list.forEach(t -> {
			int count = ThreadLocalRandom.current().nextInt(0, 99 + 1);
			t.setCount(count);
		});
		String content = JsonUtils.collectionToJSON(list);
		System.out.println(content);
		FilesUtils.writeStringToFile(path, content, false);
	}

}
