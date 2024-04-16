package com.exception;

import com.dto.request.ApiResponse;
import com.enums.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    // Exception không thể kiểm soát
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> exceptionHandling(RuntimeException exp) {
        ApiResponse apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Exception có thể xử lý được
    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> appExceptionHandling(AppException exp) {
        // Lấy ra ErrorCode từ Exception
        ErrorCode errorCode = exp.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    // Exception từ validation
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> appExceptionHandling(MethodArgumentNotValidException exp) {
        // Lấy key
        String errorKey = exp.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_CODE;
        try {
            errorCode = ErrorCode.valueOf(errorKey);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
