package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewsController {

    @Autowired
    private FileStoreService fileStoreService;

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

    @GetMapping("/ftp/upload-ajax")
    public String uploadAjax() {
        return "upload-ajax";
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

    // Download using Ajax
    @GetMapping(value = "/ftp/download-ajax")
    public String downloadAjax(Model model) {
        List<FileStore> files = fileStoreService.findAll();
        model.addAttribute("files", files);
        return "download-ajax";
    }

    @GetMapping("/ftp/upload-auto-delete")
    public String downloadLargeFile() {
        return "upload-auto-delete";
    }

}
