package com.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.List;

@Data
public class CaptchaResponse {

    private Boolean success;
    private Date timestamp;
    private String hostname;

    @JsonProperty("error-codes")
    private List<String> errorCodes;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
