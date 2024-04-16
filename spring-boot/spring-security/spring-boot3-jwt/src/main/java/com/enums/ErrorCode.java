package com.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_EXIST(1001, "User already exist"),
    USER_NOT_FOUND(1002, "User not found"),
    INVALID_CODE(1003, "Invalid error code"),
    USERNAME_INVALID(1004, "Username must be at least 3 characters"),
    INVALID_PASSWORD(1005, "Password must be at least 6 characters"),
    ;

    private final int code;
    private final String message;

}
