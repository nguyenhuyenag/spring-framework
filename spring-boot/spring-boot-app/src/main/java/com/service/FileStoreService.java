package com.service;

import com.entity.FileStore;

public interface FileStoreService {

	FileStore findByFileId(String id);
	
	void save(FileStore fileStore);

}
