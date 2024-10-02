package com.controller;

import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

// @Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("ftp")
public class AjaxController {

    private final FileStoreService fileStoreService;

    @PostMapping("/upload-ajax")
    public ResponseEntity<?> upload(@RequestParam("file-upload") MultipartFile fileUpload) {
        if (fileUpload == null || fileUpload.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            return ResponseEntity.ok(fileStoreService.saveFile(fileUpload));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @PostMapping("/upload-multiple-ajax")
    public ResponseEntity<?> uploadMultiple(@RequestParam("files-upload") MultipartFile[] filesUpload) {
        if (filesUpload == null || filesUpload.length == 0) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        try {
            return ResponseEntity.ok(fileStoreService.saveMultipleFile(filesUpload));
        } catch (Exception e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

}
