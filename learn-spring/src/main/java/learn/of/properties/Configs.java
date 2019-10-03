package learn.of.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Những properties nào bắt đầu là “config” và phần còn lại trùng với tên thuộc
 * tính trong class Configs sẽ được map giá trị. Lưu ý, phần còn lại của những
 * properties này không phân biệt hoa, thường, gạch dưới hay gạch ngang
 *
 */
@Component
@ConfigurationProperties("config") // <- prefix
@PropertySource("classpath:config.properties")
public class Configs {

	private String url;

	private List<String> version;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getVersion() {
		return version;
	}

	public void setVersion(List<String> version) {
		this.version = version;
	}

}
