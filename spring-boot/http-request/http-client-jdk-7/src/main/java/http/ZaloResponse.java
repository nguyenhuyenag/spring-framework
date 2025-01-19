package http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZaloResponse {

    @JsonProperty("error")
    private int error;
    
    private Object message;

}
