package learn.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
// @RestController
public class HelloController {

	@RequestMapping("/")
	private ResponseEntity<String> init() {
		return new ResponseEntity<>("Hello", HttpStatus.OK);
	}

}
