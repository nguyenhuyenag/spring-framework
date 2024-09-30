package com.service;

import com.entity.FileStore;
import com.repository.FileStoreRepository;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileStoreService {

    private final FileStoreRepository fileStoreRepository;

    public FileStore findByFileId(String id) {
        return fileStoreRepository.findByFileId(id).orElseGet(null);
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

    public void saveFile(MultipartFile multipartFile) throws IOException {
        fileStoreRepository.save(toFileStore(multipartFile));
    }

    public void saveMultipleFile(MultipartFile[] multipartFiles) throws IOException {
        List<FileStore> fileStoreList = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            fileStoreList.add(toFileStore(multipartFile));
            // System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
        }
        if (!fileStoreList.isEmpty()) {
            fileStoreRepository.saveAll(fileStoreList);
        }
    }

//    private void save(FileStore entity) {
//        fileStoreRepository.save(entity);
//    }

    public List<FileStore> findAll() {
        return fileStoreRepository.findAll();
    }

}
