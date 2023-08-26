package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.util.Constants;
import com.util.VerifyUtils;

@Controller
public class ReCaptcha {

	@GetMapping({ "/", "recaptcha" })
	public String login(Model model) {
		model.addAttribute("SITE_KEY", Constants.SITE_KEY);
		return "recaptcha";
	}

	@PostMapping("recaptcha")
	public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("SITE_KEY", Constants.SITE_KEY);
		String userName = request.getParameter("username");
		String password = request.getParameter("password");

		boolean valid = true;
		String errorString = null;

		// Check userName & password
		if (!"abc".equals(userName) || !"123".equals(password)) {
			valid = false;
			errorString = "UserName or Password invalid!";
		}

		if (valid) {
			String captchaResponse = request.getParameter("g-recaptcha-response");
			System.out.println("Captcha response: " + captchaResponse);
			// Verify CAPTCHA
			valid = VerifyUtils.verify(captchaResponse);
			// valid = VerifyUtils.validateCaptcha(captchaResponse);
			if (!valid) {
				errorString = "Captcha invalid!";
			}
		}
		if (!valid) {
			request.setAttribute("errorString", errorString);
			return "recaptcha";
		}
		return "redirect:/userInfo";
	}
	
	@GetMapping("userInfo")
	public String userInfo() {
		return "userInfo";
	}

}
