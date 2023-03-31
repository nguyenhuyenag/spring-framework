package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer implements CommandLineRunner {

	// WAR
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	// JAR
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	public void todo() {
//		List<String> permittedUrls = new ArrayList<>();
//		for (String antPattern : http.getSharedObject(RequestMatcher.class).stream()
//				.filter(matcher -> matcher instanceof AntPathRequestMatcher)
//				.map(matcher -> (AntPathRequestMatcher) matcher)
//				.filter(matcher -> matcher.getMatcher().pattern().equals("/public/**"))
//				.map(matcher -> matcher.getPattern()).collect(Collectors.toList())) {
//			permittedUrls.add(antPattern);
//		}
//	}

//	public void testMatch() {
//		AntPathMatcher antPathMatcher = new AntPathMatcher();
//		String[] patterns = { "/auth/**" };
//		String path = "/auth/login";
//		for (String pattern : patterns) {
//			if (antPathMatcher.match(pattern, path)) {
//				System.out.println("Matched!");
//			} else {
//				System.out.println("Not Matched!");
//			}
//		}
//	}

	@Override
	public void run(String... args) throws Exception {
		// testMatch();
	}

}
