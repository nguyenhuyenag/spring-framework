package com.springboot.excel.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private final ErrorOutput errorOutput;
    private final Object[] args;

    public CustomException(ErrorOutput errorOutput) {
        super(errorOutput.getErrorCode());
        this.errorOutput = errorOutput;
        this.args = null;
    }

    public CustomException(ErrorOutput errorOutput, Object... args) {
        super(errorOutput.getErrorCode());
        this.errorOutput = errorOutput;
        this.args = args;
    }
}
