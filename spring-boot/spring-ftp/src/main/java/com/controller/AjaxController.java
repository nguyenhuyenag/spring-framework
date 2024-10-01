package com.controller;

import com.service.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ftp")
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

//    // Download using Ajax
//    @GetMapping(value = "/download-ajax")
//    public String downloadAjax(Model model) {
//        List<FileStore> files = fileStoreService.findAll();
//        model.addAttribute("files", files);
//        return "download-ajax";
//    }

//    @ResponseBody
//    @PostMapping(value = "/download-ajax")
//    public void downloadAjax(HttpServletResponse response, String fileId) throws Exception {
//        try {
//            FileStore fileInfo = fileStoreService.findByFileId(fileId);
//            String fileBase64 = fileInfo.getFileBase64();
//
//            byte[] fileData = Base64Utils.decodeToByte(fileBase64);
//            File tempFile = File.createTempFile("tmp_", fileInfo.getFileName());
//            Files.write(tempFile.toPath(), fileData);
//            // Có thể dùng cách tương tự ở trên. Ở đây dùng TempFile để test
//            // guessContentTypeFromStream()
//            try (FileInputStream in = new FileInputStream(tempFile)) {
//                // Set file to header
//                response.setContentType(URLConnection.guessContentTypeFromStream(in));
//                response.setContentLength(fileData.length);
//                response.setHeader("Content-Disposition", "attachment; filename=\"" + fileInfo.getFileName() + "\"");
//                FileCopyUtils.copy(in, response.getOutputStream());
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                tempFile.deleteOnExit();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @PostMapping(value = "/download-ajax-base64")
//    public ResponseEntity<?> downloadAjaxBase64(String fileId) throws Exception {
//        Map<String, String> map = new HashMap<>();
//        FileStore fileInfo = fileStoreService.findByFileId(fileId);
//        map.put("filename", fileInfo.getFileName());
//        map.put("base64", fileInfo.getFileBase64());
//        return ResponseEntity.ok(map);
//    }

}
