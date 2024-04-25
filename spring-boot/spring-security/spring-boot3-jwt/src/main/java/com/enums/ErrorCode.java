package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 500 -> Lỗi không xác định được
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),

    // 400
    USER_EXIST(1001, "User already exist", HttpStatus.BAD_REQUEST),
    INVALID_CODE(1002, "Invalid error code", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least 3 characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters", HttpStatus.BAD_REQUEST),

    // 404
    USER_NOT_FOUND(1005, "User not found", HttpStatus.NOT_FOUND),

    // 401
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),

    // 403
    FORBIDDEN(1007, "You do not have permission", HttpStatus.FORBIDDEN),

    INVALID_BIRTHDAY(1008, "Invalid date of birth", HttpStatus.BAD_REQUEST),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

}
