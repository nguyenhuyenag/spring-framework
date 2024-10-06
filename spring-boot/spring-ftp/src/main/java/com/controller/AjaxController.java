package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
// @RestController
@Controller
@RequestMapping("ftp")
public class AjaxController {

    private final FileStoreService fileStoreService;

    @GetMapping("/upload-ajax")
    public String uploadAjaxView() {
        return "upload-ajax";
    }

    // Download using Ajax
    @GetMapping(value = "/download-ajax")
    public String downloadAjax(Model model) {
        List<FileStore> files = fileStoreService.findAll();
        model.addAttribute("files", files);
        return "download-ajax";
    }

    @ResponseBody
    @PostMapping("/upload-ajax")
    public ResponseEntity<?> upload(@RequestParam("file-upload") MultipartFile fileUpload) {
        if (fileUpload == null || fileUpload.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            return ResponseEntity.ok(fileStoreService.saveFile(fileUpload));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping("/upload-multiple-ajax")
    public ResponseEntity<?> uploadMultiple(@RequestParam("files-upload") MultipartFile[] filesUpload) {
        if (filesUpload == null || filesUpload.length == 0) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            return ResponseEntity.ok(fileStoreService.saveMultipleFile(filesUpload));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ResponseBody
    @PostMapping(value = "/download-ajax")
    public void downloadAjax(HttpServletResponse response, String fileId) {
        try {
            FileStore fileInfo = fileStoreService.findByFileId(fileId);
            byte[] fileData = fileInfo.getFileByte();

            // Set headers for the response
            response.setContentType(URLConnection.guessContentTypeFromName(fileInfo.getFileName()));
            response.setContentLength(fileData.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");

            // Stream file data directly to response output stream
            try (OutputStream out = response.getOutputStream()) {
                FileCopyUtils.copy(fileData, out);
            }
        } catch (Exception e) {
            log.error("Error while downloading file: {}", fileId, e);
        }
    }

}
