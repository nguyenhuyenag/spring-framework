package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filter.TokenHandler;

@RestController
@RequestMapping("auth")
public class AuthController {

	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private RedisTemplate<String, String> redis;

	@PostMapping("logout")
	public boolean doLogout(HttpServletRequest req) {
		String jwt = TokenHandler.getJwtFromRequest(req);
		String username = TokenHandler.getUsername(jwt);
		boolean check = redis.delete(username);
		if (check) {
			LOG.info("Delete Jwt of `" + username + "` on Redis.");
		}
		return check;
	}

}
