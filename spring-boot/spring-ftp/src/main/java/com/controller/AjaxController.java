package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

//    @PostMapping("upload-ajax-base64")
//    public String uploadAjax(@RequestBody Map<String, String> reqData) {
//        System.out.println(reqData);
//        return "upload-ajax";
//    }

//    @ResponseBody
//    @PostMapping(value = "/download-ajax")
//    public void downloadAjax(HttpServletResponse response, String fileId) {
//        try {
//            FileStore fileInfo = fileStoreService.findByFileId(fileId);
//            byte[] fileData = fileInfo.getFileByte();
//            File tempFile = File.createTempFile("tmp_", fileInfo.getFileName());
//            Files.write(tempFile.toPath(), fileData);
//            // Có thể dùng cách tương tự ở trên. Ở đây dùng TempFile để test
//            // guessContentTypeFromStream()
//            try (FileInputStream fis = new FileInputStream(tempFile)) {
//                // Set file to header
//                response.setContentType(URLConnection.guessContentTypeFromStream(fis));
//                response.setContentLength(fileData.length);
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");
//                FileCopyUtils.copy(fis, response.getOutputStream());
//            } finally {
//                tempFile.deleteOnExit();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

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

    @PostMapping(value = "/download-ajax-base64")
    public ResponseEntity<?> downloadAjaxBase64(String fileId) {
        Map<String, String> map = new HashMap<>();
        FileStore fileInfo = fileStoreService.findByFileId(fileId);
        map.put("filename", fileInfo.getFileName());
        map.put("base64", fileInfo.getFileBase64());
        return ResponseEntity.ok(map);
    }

}
