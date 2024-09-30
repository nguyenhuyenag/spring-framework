package com.controller;

import com.dto.request.MultiFile;
import com.dto.request.UploadRequest;
import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ftp")
@RequiredArgsConstructor
public class UploadController {

    private final FileStoreService fileStoreService;

    @PostMapping("/upload")
    public String upload(UploadRequest myFile) {
        try {
            fileStoreService.saveFile(myFile.getMultipartFile());
        } catch (Exception e) {
            e.printStackTrace();
            // model.addAttribute("message", e.getMessage());
        }
        return "upload";
    }

    @PostMapping("/upload-multiple-files")
    public String multiUpload(MultiFile myFile) {
        try {
            fileStoreService.saveMultipleFile(myFile.getMultipartFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "upload-multiple-files";
    }

}
