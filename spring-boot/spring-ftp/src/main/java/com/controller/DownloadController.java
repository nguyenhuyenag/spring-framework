package com.controller;

import com.entity.FileStore;
import com.service.FileStoreService;
import com.util.Base64Utils;
import com.util.MediaTypeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        // String fileBase64 = file.getFileBase64();
        byte[] data = Base64Utils.decodeToByte(file.getFileBase64());
        return ResponseEntity.ok() //
                .contentType(mediaType) //
                .contentLength(data.length) //
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getFileName()) //
                .body(new ByteArrayResource(data));
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
