package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("ftp")
public class ViewsController {

    private final FileStoreService fileStoreService;

    @GetMapping("/upload-ajax")
    public String uploadAjaxView() {
        return "upload-ajax";
    }

    @GetMapping("/download")
    public String downloadView(Model model) {
        List<FileStore> files = fileStoreService.findAll();
        model.addAttribute("files", files);
        return "download";
    }

    @GetMapping("/upload-auto-delete")
    public String downloadLargeFile() {
        return "upload-auto-delete";
    }

}
