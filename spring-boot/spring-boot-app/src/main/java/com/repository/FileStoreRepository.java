package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.FileStore;

// @Repository
public interface FileStoreRepository extends JpaRepository<FileStore, String> {

	Optional<FileStore> findByFileId(String fileId);

}
