package com.exception;

import com.payload.response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger LOG = LoggerFactory.getLogger(GlobalExeptionHandler.class);

    // Những lỗi không kiểm soát được
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception exp) {
        String message = exp.getMessage();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(9999);
        apiResponse.setMessage(message);
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> handlingAppException(AppException exp) {
        ErrorCode errorCode = exp.getErrorCode();
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> argumentNotValidException(MethodArgumentNotValidException ex) {
        // String message = ex.getMessage();
        // String message = ex.getFieldError().getDefaultMessage();

        String errorKey = ex.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_CODE;
        try {
            errorCode = ErrorCode.valueOf(errorKey);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());
        return ResponseEntity.badRequest().body(apiResponse);
    }

}
