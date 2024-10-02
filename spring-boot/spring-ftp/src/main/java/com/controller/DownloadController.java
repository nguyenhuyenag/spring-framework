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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("ftp")
@RequiredArgsConstructor
public class DownloadController {

    private final FileStoreService fileStoreService;

    @GetMapping("/download-file")
    public ResponseEntity<ByteArrayResource> download(@RequestParam(defaultValue = "XYZ") String fileId) {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
        // System.out.println("mediaType: " + mediaType);
        // System.out.println("fileName: " + file.getFileName());
        String fileBase64 = file.getFileBase64();
        byte[] data = Base64Utils.decodeToByte(fileBase64);
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
    }

    @PostMapping("/download-from-url")
    public ResponseEntity<Resource> downloadFromUrl(String fileId) {
        FileStore file = fileStoreService.findByFileId(fileId);
        MediaType mediaType = MediaTypeUtils.fromFileName(file.getFileName());
        String fileBase64 = file.getFileBase64();
        byte[] data = Base64Utils.decodeToByte(fileBase64);
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
    }

}
