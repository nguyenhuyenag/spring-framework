package com.service;

import com.entity.FileStore;
import com.repository.FileStoreRepository;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileStoreService {

    private final FileStoreRepository fileStoreRepository;

    public FileStore findByFileId(String id) {
        return fileStoreRepository.findByFileId(id)
                .orElseThrow(() -> new IllegalArgumentException("FileStore with id=" + id + " not found"));
    }


    // Convert MultipartFile -> MultipartFile
    public FileStore toFileStore(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileStore entity = new FileStore();
        entity.setFileName(fileName);
        // Set Base 64
        entity.setFileBase64(Base64Utils.encodeToString(multipartFile.getBytes()));
        // Set byte[]
        entity.setFileByte(multipartFile.getBytes());
        // Set file size
        entity.setFileSize((float) multipartFile.getSize() / 1_000_000 + " MB");
        return entity;
    }

    public String saveFile(MultipartFile file) throws IOException {
        FileStore entity = fileStoreRepository.save(toFileStore(file));
        return entity.getFileId();
    }

    public List<String> saveMultipleFile(MultipartFile[] multipartFiles) throws IOException {
        List<FileStore> fileStoreList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            fileStoreList.add(toFileStore(multipartFile));
        }
        if (!fileStoreList.isEmpty()) {
            List<FileStore> fileStores = fileStoreRepository.saveAll(fileStoreList);
            return fileStores.stream().map(FileStore::getFileId).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public List<FileStore> findAll() {
        return fileStoreRepository.findAll();
    }

}
