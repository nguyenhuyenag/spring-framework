package com.google.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.util.GooglePojo;
import com.google.util.GoogleUtils;

@Controller
public class ApiController {

	@Autowired
	private Environment env;

	@Autowired
	private GoogleUtils googleUtils;

	@RequestMapping({ "/", "login" })
	public String login(Model model) {
		model.addAttribute("HOSTNAME", env.getProperty("google.redirect.uri"));
		model.addAttribute("CLIENT_ID", env.getProperty("google.app.id"));
		return "login";
	}

	@RequestMapping("login-google")
	public String loginGoogle(HttpServletRequest request, Model model) throws ClientProtocolException, IOException {
		model.addAttribute("HOSTNAME", "http://localhost:8080/login-google");
		model.addAttribute("CLIENT_ID", env.getProperty("google.app.id"));
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return "redirect:/login?google=error";
		}
		String accessToken = googleUtils.getToken(code);
		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/user";
	}

	@RequestMapping("user")
	public String user() {
		return "user";
	}

	@RequestMapping("admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("403")
	public String accessDenied() {
		return "403";
	}
}
