package com.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.boot.properties.ReadBasicConfiguration;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	ReadBasicConfiguration configuration;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(configuration.getUrl());
		System.out.println(configuration.getLanguage());
		List<String> version = configuration.getVersion();
		version.forEach(System.out::println);
	}

}
