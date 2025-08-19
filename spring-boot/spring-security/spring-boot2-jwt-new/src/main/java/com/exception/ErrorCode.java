package com.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    INVALID_CODE(1000, "ErrorCode -> Invalid message code"),
    USER_EXIST(1001, "ErrorCode -> User exits"),
    USERNAME_INVALID(1002, "ErrorCode -> Username must be at least 3 characters"),
    PASSWORD_INVALID(1003, "ErrorCode -> Password must be at least 6 characters");

    private final int code;
    private final String message;

}
