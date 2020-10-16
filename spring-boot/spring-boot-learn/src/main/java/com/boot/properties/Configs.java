package com.boot.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/*-
 * - Nếu các properties không bắt đầu với “config” thì không cần khai báo prefix
 * trong annotation @ConfigurationProperties
 * 
 * - Nếu file `configs.properties` không tồn tại => throws FileNotFoundException, sử dụng `ignoreResourceNotFound=true`
 */
// @Getter
// @Setter
@Component
@ConfigurationProperties()
@PropertySource("classpath:configs.properties")
// @PropertySource("classpath:configs.properties", ignoreResourceNotFound=true) // ignore the not found
public class Configs {

	private String url;
	private Phone phone;
	private List<String> version;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public List<String> getVersion() {
		return version;
	}

	public void setVersion(List<String> version) {
		this.version = version;
	}

}

// @Getter
// @Setter
class Phone {

	private String os;
	private String name;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
