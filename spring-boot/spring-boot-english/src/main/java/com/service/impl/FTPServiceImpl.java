package com.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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
		Path path = Paths.get("file/data.json");
		List<Vocabulary> list = repository.findAllByOrderByWord();
		String content = JsonUtils.collectionToJSON(list);
		System.out.println(content);
		FilesUtils.writeStringToFile(path, content, false);
	}

}
