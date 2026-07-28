package com.recaptcha;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ReCaptchaV2Response extends ReCaptchaResponse {

    @JsonProperty("challenge_ts")
    private String challengeTs;

}
