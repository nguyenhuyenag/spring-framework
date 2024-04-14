package com.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_EXIST(1001, "ErrorCode -> User exits");

    private int code;
    private String message;

}
