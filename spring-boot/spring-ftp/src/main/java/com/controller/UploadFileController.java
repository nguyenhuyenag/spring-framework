package com.controller;

import com.entity.FileStore;
import com.dto.request.MultiFile;
import com.dto.request.MyFile;
import com.service.FileStoreService;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Upload và luu file dưới dạng Base64 vào DB
 */
@Controller
@RequestMapping("/ftp")
@RequiredArgsConstructor
public class UploadFileController {

    private final FileStoreService fileStoreService;

    @PostMapping("/upload")
    public String upload(MyFile myFile, Model model) {
        try {
            MultipartFile multipartFile = myFile.getMultipartFile();
            fileStoreService.save(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
        }
        return "upload";
    }

    @PostMapping("/multi-upload")
    public String multiUpload(MultiFile myFile) {
        try {
            MultipartFile[] multipartFiles = myFile.getMultipartFile();
            for (MultipartFile multipartFile : multipartFiles) {
                fileStoreService.save(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "multi-upload";
    }

//    private void saveFile(MultipartFile multipartFile) throws IOException {
//        String fileName = multipartFile.getOriginalFilename();
//        FileStore file = new FileStore();
//        file.setFileName(fileName);
//        // Set Base 64
//        file.setFileBase64(Base64Utils.encodeToString(multipartFile.getBytes()));
//        // Set byte[]
//        file.setFileByte(multipartFile.getBytes());
//        // Save file
//        fileStoreService.save(file);
//        System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
//    }

}
