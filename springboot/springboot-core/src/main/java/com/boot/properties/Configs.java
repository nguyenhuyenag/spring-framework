package com.boot.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/*-
 * - Nếu các properties không bắt đầu với “config” thì không cần khai báo prefix
 * trong annotation @ConfigurationProperties
 * 
 * - Nếu file `configs.properties` không tồn tại => throws FileNotFoundException, sử dụng `ignoreResourceNotFound=true`
 */
@Getter
@Setter
@Component
@ConfigurationProperties()
@PropertySource("classpath:configs.properties")
// @PropertySource("classpath:configs.properties", ignoreResourceNotFound=true) // ignore the not found
public class Configs {

	private String url;
	private Phone phone;
	private List<String> version;

}

@Getter
@Setter
class Phone {

	private String os;
	private String name;

}
