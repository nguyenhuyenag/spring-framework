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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("/ftp")
@RequiredArgsConstructor
public class UploadController {

    private final FileStoreService fileStoreService;

    @PostMapping("/upload")
    public String upload(MyFile myFile, Model model) {
        try {
            MultipartFile multipartFile = myFile.getMultipartFile();
            saveFileAsBase64(multipartFile);
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
                saveFileAsBase64(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "multi-upload";
    }

    @PostMapping("/upload-ajax")
    public String uploadAjax(@RequestParam("file") MultipartFile file) throws IOException {
        saveFileAsBase64(file);
        return "upload-ajax";
    }

    @PostMapping("upload-ajax-base64")
    public String uploadAjax(@RequestBody Map<String, String> reqData) {
        System.out.println(reqData);
        return "upload-ajax";
    }

    private void saveFileAsBase64(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileStore fileStore = new FileStore();
        fileStore.setFileName(fileName);
        String content = Base64Utils.encodeToString(multipartFile.getBytes());
        fileStore.setFileContent(content);
        fileStoreService.save(fileStore);
        System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
    }

}
