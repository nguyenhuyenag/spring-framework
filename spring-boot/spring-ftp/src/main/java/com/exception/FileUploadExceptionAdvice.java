package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@ControllerAdvice
public class FileUploadExceptionAdvice {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFilesize;

    // TODO: https://stackoverflow.com/a/54405341/7068014
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxUploadSizeExceeded(MaxUploadSizeExceededException ex) {
        log.error("File upload error: {}", ex.getMessage());
        String message = "The maximum upload size " + maxFilesize;
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE)
                            .body(message);
    }

}
