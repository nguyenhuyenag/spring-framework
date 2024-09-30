package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.Base64Utils;
import com.util.MediaTypeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("ftp")
@RequiredArgsConstructor
public class DownloadController {

    private final FileStoreService fileStoreService;

//    @PostMapping("upload")
//    public String upload(MyFile myFile) {
//        try {
//            MultipartFile multipartFile = myFile.getMultipartFile();
//            String fileName = multipartFile.getOriginalFilename();
//            FileStore fileStore = new FileStore();
//            fileStore.setFileName(fileName);
//            String content = Base64Utils.encodeToString(multipartFile.getBytes());
//            fileStore.setFileContent(content);
//            fileStoreService.save(fileStore);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "upload";
//    }
//
//    @PostMapping("multi-upload")
//    public String multiUpload(MultiFile myFile) {
//        try {
//            MultipartFile[] multipartFiles = myFile.getMultipartFile();
//            for (MultipartFile multipartFile : multipartFiles) {
//                String fileName = multipartFile.getOriginalFilename();
//                FileStore fileStore = new FileStore();
//                fileStore.setFileName(fileName);
//                String content = Base64Utils.encodeToString(multipartFile.getBytes());
//                fileStore.setFileContent(content);
//                fileStoreService.save(fileStore);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "multi-upload";
//    }

    @GetMapping("/download-file")
    public ResponseEntity<ByteArrayResource> download(@RequestParam(defaultValue = "XYZ") String fileId) {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
        // System.out.println("mediaType: " + mediaType);
        // System.out.println("fileName: " + file.getFileName());
        String fileContent = file.getFileContent();
        byte[] data = Base64Utils.decodeToByte(fileContent);
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
    }

    @PostMapping("/download-from-url")
    public ResponseEntity<Resource> downloadFromUrl(String fileId) throws IOException {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
        String fileContent = file.getFileContent();
        byte[] data = Base64Utils.decodeToByte(fileContent);
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
    }

    @ResponseBody
    @PostMapping(value = "/download-ajax")
    public void downloadAjax(HttpServletResponse response, String fileId) throws Exception {
        try {
            FileStore fileInfo = fileStoreService.findByFileId(fileId);
            String fileContent = fileInfo.getFileContent();

            byte[] fileData = Base64Utils.decodeToByte(fileContent);
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
        map.put("base64", fileInfo.getFileContent());
        // System.out.println("map: " + map);
        return ResponseEntity.ok(map);
    }

}
