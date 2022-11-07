package com.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.FileStore;
import com.repository.FileStoreRepository;
import com.service.FileStoreService;

@Service
public class FileStoreServiceImpl implements FileStoreService {

	@Autowired
	private FileStoreRepository fileStoreRepository;

	@Override
	public FileStore findByFileId(String id) {
		Optional<FileStore> opt = fileStoreRepository.findByFileId(id);
		return opt.orElseGet(null);
	}
	
	@Override
	public void save(FileStore fileStore) {
		fileStoreRepository.save(fileStore);
	}

	@Override
	public List<FileStore> findAll() {
		return fileStoreRepository.findAll();
	}

}
