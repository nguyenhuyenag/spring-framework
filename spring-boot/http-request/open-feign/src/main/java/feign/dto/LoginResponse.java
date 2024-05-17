package feign.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    @JsonProperty("access_token")
    private String accessToken;
    private String refresh_token;

}
