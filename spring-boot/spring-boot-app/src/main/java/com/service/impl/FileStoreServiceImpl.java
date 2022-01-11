package com.service.impl;

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
	public FileStore findById(String id) {
		Optional<FileStore> opt = fileStoreRepository.findById(id);
		return opt.orElseGet(null);
	}

}
