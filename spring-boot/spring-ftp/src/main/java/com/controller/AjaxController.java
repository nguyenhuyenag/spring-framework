package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.Base64Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Upload và luu file dưới dạng Base64 vào DB
 */
@RequiredArgsConstructor
@Controller
@RequestMapping("/ftp")
public class AjaxController {

    private final FileStoreService fileStoreService;

    @GetMapping("/upload-ajax")
    public String uploadAjax() {
        return "upload-ajax";
    }

    @PostMapping("/upload-ajax")
    public String uploadAjax(@RequestParam("file") MultipartFile file) throws IOException {
        fileStoreService.saveFile(file);
        return "upload-ajax";
    }

    @PostMapping("upload-ajax-base64")
    public String uploadAjax(@RequestBody Map<String, String> reqData) {
        System.out.println(reqData);
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
    @PostMapping(value = "/download-ajax")
    public void downloadAjax(HttpServletResponse response, String fileId) throws Exception {
        try {
            FileStore fileInfo = fileStoreService.findByFileId(fileId);
            String fileBase64 = fileInfo.getFileBase64();

            byte[] fileData = Base64Utils.decodeToByte(fileBase64);
            File tempFile = File.createTempFile("tmp_", fileInfo.getFileName());
            Files.write(tempFile.toPath(), fileData);
            // Có thể dùng cách tương tự ở trên. Ở đây dùng TempFile để test
            // guessContentTypeFromStream()
            try (FileInputStream in = new FileInputStream(tempFile)) {
                // Set file to header
                response.setContentType(URLConnection.guessContentTypeFromStream(in));
                response.setContentLength(fileData.length);
                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");
                FileCopyUtils.copy(in, response.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                tempFile.deleteOnExit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/download-ajax-base64")
    public ResponseEntity<?> downloadAjaxBase64(String fileId) throws Exception {
        Map<String, String> map = new HashMap<>();
        FileStore fileInfo = fileStoreService.findByFileId(fileId);
        map.put("filename", fileInfo.getFileName());
        map.put("base64", fileInfo.getFileBase64());
        // System.out.println("map: " + map);
        return ResponseEntity.ok(map);
    }

//    private void saveFile(MultipartFile multipartFile) throws IOException {
//        String fileName = multipartFile.getOriginalFilename();
//        FileStore file = new FileStore();
//        file.setFileName(fileName);
//        // Set Base 64
//        file.setFileBase64(Base64Utils.encodeToString(multipartFile.getBytes()));
//        // Set byte[]
//        file.setFileByte(multipartFile.getBytes());
//        // Save file
//        fileStoreService.save(file);
//        System.out.println("Size: " + (float) multipartFile.getSize() / 1_000_000 + " MB");
//    }

}
