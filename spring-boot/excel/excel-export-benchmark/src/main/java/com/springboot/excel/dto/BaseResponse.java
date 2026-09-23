package com.springboot.excel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BaseResponse {
    
    @JsonProperty("error_schema")
    private ErrorSchema errorSchema;

    @JsonProperty("output_schema")
    private Object outputSchema;

    public BaseResponse() {}

    public BaseResponse(ErrorSchema errorSchema, Object outputSchema) {
        this.errorSchema = errorSchema;
        this.outputSchema = outputSchema;
    }

}
