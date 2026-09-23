package com.springboot.excel.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorOutput {

    OK("VM-00-000", HttpStatus.OK, "Success"),
    CAR_STILL_IN_USE("VM-00-023", HttpStatus.BAD_REQUEST, "Car type still in use"),
    INTERNAL_SERVER_ERROR("VM-99-500", HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");

    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String defaultMessage;

    ErrorOutput(String errorCode, HttpStatus httpStatus, String defaultMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.defaultMessage = defaultMessage;
    }
}
