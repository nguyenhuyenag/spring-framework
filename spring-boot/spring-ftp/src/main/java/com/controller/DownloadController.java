package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.MediaTypeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

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

}
