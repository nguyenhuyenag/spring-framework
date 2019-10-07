package learn.of.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import learn.util.Info;

@Controller
public class WebController {

	// Nhận request GET
	@GetMapping("/") // Nếu người dùng request tới địa chỉ "/"
	public String index() {
		return "index"; // Trả về file index.html
	}

	@GetMapping("hello")
	// Model là một object của Spring, được gắn vào trong mọi request
	public String hello(@RequestParam(name = "name", defaultValue = "Java") String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}

	@GetMapping("profile")
	public String profile(Model model) {
		List<Info> list = new ArrayList<>();
		list.add(new Info("Nickname", "nguyenhuyenag"));
		list.add(new Info("Git", "https://github.com/nguyenhuyenag"));
		// Đưa thông tin vào Model
		model.addAttribute("listProfile", list);
		// Trả về template profile.html
		return "profile";
	}
}
