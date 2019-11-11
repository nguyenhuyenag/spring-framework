package jwt.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class PropertiesReader {

	public static String jwtSecretKey;

	@Value("${security.jwt.token.secret-key}")
	private void setJwtSecretKey(String key) {
		PropertiesReader.jwtSecretKey = key;
	}

}
