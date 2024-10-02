package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.MediaTypeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("ftp")
public class DownloadController {

    private final FileStoreService fileStoreService;

    @GetMapping("/download-file")
    public ResponseEntity<ByteArrayResource> download(@RequestParam(defaultValue = "XYZ") String fileId) {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
        byte[] data = file.getFileByte();
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
    }

//    @GetMapping("/download-streaming")
//    public ResponseEntity<StreamingResponseBody> downloadStreaming(@RequestParam(defaultValue = "XYZ") String fileId) {
//        FileStore file = fileStoreService.findByFileId(fileId);
//        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
//
//        StreamingResponseBody stream = out -> {
//            try (InputStream inputStream = new ByteArrayInputStream(file.getFileByte())) {
//                FileCopyUtils.copy(inputStream, out);
//            }
//        };
//
//        return ResponseEntity.ok()
//                .contentType(mediaType)
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
//                .body(stream);
//    }

    @GetMapping("/download-streaming")
    public ResponseEntity<StreamingResponseBody> downloadStreaming(@RequestParam(defaultValue = "XYZ") String fileId) {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());

        // StreamingResponseBody to stream the file data
        StreamingResponseBody stream = out -> {
            try (InputStream inputStream = new ByteArrayInputStream(file.getFileByte())) {
                FileCopyUtils.copy(inputStream, out);
            }
        };

        // Create HttpHeaders and configure content disposition
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setContentDispositionFormData("attachment", file.getFileName());

        return ResponseEntity.ok()
                .headers(headers)
                .body(stream);
    }

    @GetMapping("/download-from-url")
    public String downloadFromUrlView(Model model) {
        List<FileStore> files = fileStoreService.findAll();
        model.addAttribute("files", files);
        return "download-from-url";
    }

    @PostMapping("/download-from-url")
    // ResponseEntity<Resource>
    public ResponseEntity<?> downloadFromUrl(String fileId) {
        try {
            FileStore file = fileStoreService.findByFileId(fileId);
            MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
            // String fileBase64 = file.getFileBase64();
            // byte[] data = Base64Utils.decodeToByte(fileBase64);
            byte[] data = file.getFileByte();
            return ResponseEntity.ok() //
                    .contentType(mediaType) //
                    .contentLength(data.length) //
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                    .body(new ByteArrayResource(data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

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
