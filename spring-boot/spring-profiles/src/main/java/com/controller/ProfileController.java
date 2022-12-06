package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BootApplication;

@RestController
public class ProfileController {

	@Autowired
	private Environment environment;

	@Value("${app.message}")
	private String welcomeMessage;
	
	public void getActiveProfiles() {
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}
	}

	@GetMapping("welcome")
	public String getDataBaseConnectionDetails() {
		getActiveProfiles();
		return welcomeMessage;
	}

	@GetMapping("change-profile")
	public String changeProfile(String profile) {
		BootApplication.restart(profile);
		return "Current profile: " + profile;
	}

}
