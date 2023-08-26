package com.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

	// @Autowired
	// private UserService userService;

	@GetMapping({ "/", "login" })
	public String loginPage(HttpServletRequest req) {
		Principal principal = req.getUserPrincipal();
		if (principal != null) {
			return "redirect:tracuu";
		}
		return "login";
	}

	@GetMapping("doimatkhau")
	public String doiMatKhau() {
		return "doimatkhau";
	}

//	@PostMapping("doimatkhau")
//	public ResponseEntity<String> updateMatkhau(HttpServletRequest req, String matkhauhientai, String matkhaumoi,
//			String nhaplaimatkhaumoi) {
//		Principal principal = req.getUserPrincipal();
//		String username = principal.getName();
//		String message = userService.changePassword(username, matkhauhientai, matkhaumoi, nhaplaimatkhaumoi);
//		return new ResponseEntity<>(message, HttpStatus.OK);
//	}

}
