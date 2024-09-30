package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewsController {

    private final FileStoreService fileStoreService;

    @GetMapping({"/", "home"})
    public String home() {
        return "home";
    }

    /**
     * Có tiền tố '/ftp/xxx' vì đây là view (GET) của một POST '/ftp/xxx'
     */
    @GetMapping("/ftp/upload")
    public String upload() {
        return "upload";
    }

    @GetMapping("/ftp/multi-upload")
    public String multiUpload() {
        return "multi-upload";
    }

    @GetMapping("/ftp/download")
    public String downloadView(Model model) {
        List<FileStore> files = fileStoreService.findAll();
        model.addAttribute("files", files);
        return "download";
    }

    @GetMapping("/ftp/download-from-url")
    public String downloadFromUrlView() {
        return "download-from-url";
    }

    @GetMapping("/ftp/upload-auto-delete")
    public String downloadLargeFile() {
        return "upload-auto-delete";
    }

}
