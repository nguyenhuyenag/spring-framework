package com.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@Autowired
	private Environment env;

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

	@RequestMapping({ "/", "login" })
	public String login(Model model) throws URISyntaxException {
		HttpGet httpGet = new HttpGet("https://accounts.google.com/o/oauth2/auth");
		URI uri = new URIBuilder(httpGet.getURI()) //
				.addParameter("scope", "email") //
				.addParameter("approval_prompt", "force") //
				.addParameter("response_type", "code") //
				.addParameter("redirect_uri", env.getProperty("google.redirect.uri")) //
				.addParameter("client_id", env.getProperty("google.app.id")).build();
		model.addAttribute("URL_GOOGLE", uri.toString());
		return "login";
	}

}
