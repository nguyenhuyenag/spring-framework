package com.runner;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.boot.properties.AutoReadConfiguration;

@Component
public class CommandLineRunnerStartup implements CommandLineRunner {

	@Autowired
	AutoReadConfiguration configuration;

	@Override
	public void run(String... args) throws Exception {
		// System.out.println(new Date() + " [CommandLineRunnerStartup] Run method ");
		// System.out.println(configuration.getUrl());
		// System.out.println(configuration.getGiftCode());
		// System.out.println(configuration.getLanguage());
		// List<String> version = configuration.getVersion();
		// version.forEach(System.out::println);
		// ResourceFile.readProperties();
		// System.out.println(readProperties.URL);
		// System.out.println(ReadProperties.URL);
		// List<String> version = readProperties.VERSION;
		// version.forEach(System.out::println);
	}

}
