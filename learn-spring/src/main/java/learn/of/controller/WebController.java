package learn.of.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Đánh dấu đây là một Controller
// Nơi tiếp nhận các reqquest từ phía người dùng
@Controller
public class WebController {

	// Đón nhận request GET
	@GetMapping("/") // Nếu người dùng request tới địa chỉ "/"
	public String index() {
		return "index"; // Trả về file index.html
	}

	@GetMapping("about")
	public String about() {
		return "about";
	}

	@GetMapping("hello")
	// Request param "name" sẽ được gán giá trị vào biến String
	// Model là một object của Spring Boot, được gắn vào trong mọi request
	public String hello(@RequestParam(name = "name", required = false, defaultValue = "Java") String name,
			Model model) {
		// Gắn vào model giá trị name nhận được
		model.addAttribute("name", name);
		return "hello"; // trả về file hello.html cùng với thông tin trong model
	}
}