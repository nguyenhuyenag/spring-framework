package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.FileStore;
import org.springframework.stereotype.Repository;

@Repository
public interface FileStoreRepository extends JpaRepository<FileStore, String> {

    Optional<FileStore> findByFileId(String fileId);

}
