package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Controller
@RequestMapping("/ftp")
public class AutoDeleteController {

    // private static final Logger LOG = LoggerFactory.getLogger(AutoDeleteController.class);

    @PostMapping("/upload-xfile")
    public ResponseEntity<?> downloadLargeFile(@RequestParam("xfile") MultipartFile multipartFile)
            throws IOException {
        String filename = saveFileTemp(multipartFile);
        return ResponseEntity.ok(filename);
    }

    @GetMapping("/upload-auto-delete")
    public String downloadLargeFile() {
        return "upload-auto-delete";
    }

    /**
     * Xóa file trong `/upload_tmp`
     */
    @PostMapping("/auto-delete")
    public ResponseEntity<?> deleteFile(String filename) throws IOException {
        Path file = Paths.get(SystemUtils.USER_DIR, "upload_tmp", filename);
        if (Files.exists(file)) {
            Files.delete(file);
            log.info("Delete file '{}'", file.getFileName());
        } else {
            log.info("File '{}' not found!", file.getFileName());
        }
        return ResponseEntity.ok(null);
    }

    private String saveFileTemp(MultipartFile multipartFile) throws IOException {
        Path uploadDir = Paths.get(SystemUtils.USER_DIR, "upload_tmp");

        // Create the directory if it doesn't exist
        File directory = new File(uploadDir.toString());
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a new File in the specified directory
        File file = new File(uploadDir + File.separator + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);

        if (file.exists()) {
            return file.getName();
        }
        return "";
    }

}
