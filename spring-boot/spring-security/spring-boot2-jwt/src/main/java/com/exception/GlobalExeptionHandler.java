package com.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExeptionHandler {

    /**
     * Xử lý những Exception của RuntimeException. Có thể trả về response ngay tại đây
     */
    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<String> handlingRuntimeException(RuntimeException exp) {
        String message = exp.getMessage();
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> argumentNotValidException(MethodArgumentNotValidException ex) {
        // String message = ex.getMessage();
        // FieldError fieldError = ex.getFieldError();
        String message = ex.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }

}
