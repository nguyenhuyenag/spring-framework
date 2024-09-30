package com.controller;

import com.dto.request.MultiFile;
import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ftp")
public class UploadController {

    private final FileStoreService fileStoreService;

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload") // Mapping to name="multipartFile" in form upload
    public String upload(@RequestParam("multipartFile") MultipartFile file, Model model) {
        try {
            String fileId = fileStoreService.saveFile(file);
            model.addAttribute("fileId", fileId);
        } catch (IOException e) {
            model.addAttribute("fileId", e.getMessage());
        }
        return "upload";
    }

    @GetMapping("/upload-multiple-files")
    public String uploadMultiple() {
        return "upload-multiple-files";
    }

    @PostMapping("/upload-multiple-files")
    public String uploadMultiple(MultiFile myFile) {
        try {
            fileStoreService.saveMultipleFile(myFile.getMultipartFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload-multiple-files";
    }

}
