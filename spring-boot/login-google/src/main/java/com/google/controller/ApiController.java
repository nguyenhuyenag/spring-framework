package com.google.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
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
	public String login(Model model) throws URISyntaxException {
		HttpGet httpGet = new HttpGet("https://accounts.google.com/o/oauth2/auth");
		URI uri = new URIBuilder(httpGet.getURI())
				.addParameter("scope", "email")
				.addParameter("approval_prompt", "force")
				.addParameter("response_type", "code")
				.addParameter("redirect_uri", env.getProperty("google.redirect.uri"))
				.addParameter("client_id", env.getProperty("google.app.id")).build();
		model.addAttribute("URL", uri.toString());
		return "login";
	}

	@RequestMapping("login-google")
	public String loginGoogle(HttpServletRequest request, Model model) throws ClientProtocolException, IOException {
		// model.addAttribute("CLIENT_ID", env.getProperty("google.app.id"));
		// model.addAttribute("HOSTNAME", env.getProperty("google.redirect.uri"));
		String code = request.getParameter("code");
		if (StringUtils.isEmpty(code)) {
			return "redirect:/login?google=error";
		}
		String accessToken = googleUtils.getAssessToken(code);
		GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
		UserDetails userl = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userl, null, userl.getAuthorities());
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
