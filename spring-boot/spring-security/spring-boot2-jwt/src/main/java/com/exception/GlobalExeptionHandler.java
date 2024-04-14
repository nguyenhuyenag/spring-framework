package com.exception;

import com.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Xử lý những Exception. Có thể trả về response ngay tại đây
 */
@ControllerAdvice
public class GlobalExeptionHandler {

    // Những lỗi không kiểm soát được
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handlinException(Exception exp) {
        String message = exp.getMessage();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(9999);
        apiResponse.setMessage(message);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> handlingAppException(AppException exp) {
        ErrorCode errorCode = exp.getErrorCode();
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<String> argumentNotValidException(MethodArgumentNotValidException ex) {
        // String message = ex.getMessage();
        // FieldError fieldError = ex.getFieldError();
        String message = ex.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }

}
