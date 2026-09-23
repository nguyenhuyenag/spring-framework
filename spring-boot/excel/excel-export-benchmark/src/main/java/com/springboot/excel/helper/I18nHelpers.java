package com.springboot.excel.helper;

import com.springboot.excel.dto.BaseResponse;
import com.springboot.excel.dto.ErrorSchema;
import com.springboot.excel.exception.ErrorOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class I18nHelpers {

    private final MessageSource messageSource;

    public ResponseEntity<BaseResponse> createResponse(ErrorOutput errorOutput, Object outputSchema, Locale locale) {
        String errorMsg;
        try {
            errorMsg = messageSource.getMessage(errorOutput.getErrorCode(), null, locale);
        } catch (Exception e) {
            errorMsg = errorOutput.getDefaultMessage();
        }

        ErrorSchema errorSchema = new ErrorSchema(errorOutput.getErrorCode(), errorMsg);
        return new ResponseEntity<>(new BaseResponse(errorSchema, outputSchema), errorOutput.getHttpStatus());
    }
}
