package learn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import learn.entity.User;
import learn.repository.UserRepository;

@Controller
@RequestMapping("user")
public class BasicController {

	@Autowired
	private UserRepository repository;

	@ResponseBody
	@GetMapping("controller")
	private List<User> getAll() {
		return repository.findAll();
	}

}
