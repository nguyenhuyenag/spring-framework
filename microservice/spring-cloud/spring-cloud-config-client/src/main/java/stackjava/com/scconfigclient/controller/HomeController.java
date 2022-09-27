package stackjava.com.scconfigclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class HomeController {

	@Value("${database.host}")
	private String host;
	
	@Value("${database.database}")
	private String database;
	
	@Value("${database.username}")
	private String username;
	
	@Value("${database.password}")
	private String password;

	@GetMapping("config")
	public String config() {
		return "host: " + host + "<br/>username: " + username + "<br/>password: " + password + "<br/>database: "
				+ database;
	}
}
