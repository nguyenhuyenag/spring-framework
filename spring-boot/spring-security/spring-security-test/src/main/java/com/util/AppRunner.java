package com.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// howToUseIMUDS();
	}

	protected void howToUseIMUDS() {
		UserDetails user = inMemoryUserDetailsManager.loadUserByUsername("admin");
		System.out.println("Check user exits: " + inMemoryUserDetailsManager.userExists("admin"));
		if (user != null) {
			System.out.println(user.getUsername());
		}
	}

}
