package com.service;

import java.util.List;

import com.entity.FileStore;

public interface FileStoreService {

	List<FileStore> findAll();

	FileStore findByFileId(String id);
	
	void save(FileStore fileStore);

}
