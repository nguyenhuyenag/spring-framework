package com.service;

import com.entity.FileStore;
import com.repository.FileStoreRepository;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileStoreService {

    private final FileStoreRepository fileStoreRepository;

    public FileStore findByFileId(String id) {
        return fileStoreRepository.findByFileId(id).orElseGet(null);
    }

    public void save(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileStore file = new FileStore();
        file.setFileName(fileName);
        // Set Base 64
        file.setFileBase64(Base64Utils.encodeToString(multipartFile.getBytes()));
        // Set byte[]
        file.setFileByte(multipartFile.getBytes());
        // Save file
        save(file);
        System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
    }

    private void save(FileStore entity) {
        fileStoreRepository.save(entity);
    }

    public List<FileStore> findAll() {
        return fileStoreRepository.findAll();
    }

}
