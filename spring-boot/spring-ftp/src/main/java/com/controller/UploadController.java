package com.controller;

import com.entity.FileStore;
import com.entity.MultiFile;
import com.entity.MyFile;
import com.service.FileStoreService;
import com.util.Base64Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("ftp")
public class UploadController {

    @Autowired
    private FileStoreService fileStoreService;

    @PostMapping("upload")
    public String upload(MyFile myFile, Model model) {
        try {
            MultipartFile multipartFile = myFile.getMultipartFile();
            saveFile(multipartFile);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", e.getMessage());
        }
        return "upload";
    }

    @PostMapping("multi-upload")
    public String multiUpload(MultiFile myFile) {
        try {
            MultipartFile[] multipartFiles = myFile.getMultipartFile();
            for (MultipartFile multipartFile : multipartFiles) {
                saveFile(multipartFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "multi-upload";
    }

    @PostMapping("upload-ajax")
    public String uploadAjax(@RequestParam("file") MultipartFile file) throws IOException {
        saveFile(file);
        return "upload-ajax";
    }

    protected void saveFile(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        FileStore fileStore = new FileStore();
        fileStore.setFileName(fileName);
        String content = Base64Utils.encodeToString(multipartFile.getBytes());
        fileStore.setFileContent(content);
        fileStoreService.save(fileStore);
        System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
    }

}
