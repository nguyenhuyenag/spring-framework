package dev.boot.properties;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter                     // <- Important
@Component                  // <- Important
@ConfigurationProperties    // <- Important
@PropertySource(value = "classpath:basic.properties", encoding = "utf-8") // <- Important
public class AutoReadConfiguration {

    /*-
     * How to use? -> 	@Autowired
     * 					private AutoReadConfiguration autoReadConfiguration;
     */
    private String url;
    private String language;
    private List<String> version;

    // giftcode = giftCode = gift_code
    @Value("${gift.code}")
    private String giftCode;

}
