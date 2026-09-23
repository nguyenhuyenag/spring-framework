package com.springboot.excel.exception;

import com.springboot.excel.dto.BaseResponse;
import com.springboot.excel.helper.I18nHelpers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final I18nHelpers i18nHelpers;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse> handleCustomException(CustomException ex, Locale locale) {
        return i18nHelpers.createResponse(ex.getErrorOutput(), null, locale);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleGeneralException(Exception ex, Locale locale) {
        log.error("Unhandled Exception: ", ex);
        return i18nHelpers.createResponse(ErrorOutput.INTERNAL_SERVER_ERROR, null, locale);
    }
}
