package feign.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.entity.StringEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import feign.pojo.Login;

@RestController
public class ApiController {

//	@RequestMapping({ "/", "login" })
//	public String login(Model model) throws URISyntaxException {
//		HttpGet httpGet = new HttpGet("https://accounts.google.com/o/oauth2/auth");
//		URI uri = new URIBuilder(httpGet.getURI()) //
//				.addParameter("scope", "email") //
//				.addParameter("approval_prompt", "force") //
//				.addParameter("response_type", "code").build(); //
//				//.addParameter("redirect_uri", env.getProperty("google.redirect.uri")) //
//				//.addParameter("client_id", env.getProperty("google.app.id")).build();
//		model.addAttribute("URL_GOOGLE", uri.toString());
//		return "login";
//	}

	@GetMapping("get")
	public ResponseEntity<?> get(String author) {
		Map<String, String> map = new HashMap<>();
		map.put("name", "Java");
		map.put("version", "16");
		map.put("company", "Oracle");
		map.put("author", author);
		return ResponseEntity.ok(map);
	}

	@PostMapping("post-params")
	public ResponseEntity<?> postParams(String username, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("username", username);
		map.put("password", password);
		return ResponseEntity.ok(map);
	}

	@PostMapping("post-json")
	public ResponseEntity<?> postParams(@RequestBody Login login) {
		login.setPassword("1234567890");
		return ResponseEntity.ok(login);
	}
	
	@GetMapping("string-entity")
	public ResponseEntity<?> stringEntity() throws UnsupportedEncodingException {
		StringEntity se = new StringEntity("abc");
		return ResponseEntity.ok(se);
	}

}
