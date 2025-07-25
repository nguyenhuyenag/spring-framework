package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServerTestApplication extends SpringBootServletInitializer implements CommandLineRunner {

    // WAR
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ServerTestApplication.class);
    }

    // JAR
    public static void main(String[] args) {
        SpringApplication.run(ServerTestApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Assert.notNull(null, "password cannot be null");
        // System.out.println("TIME: " + TimeUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        // System.out.println(encoder.encode("123456"));

//		Response res = RestAssured.given()
//			      .header(HttpHeaders.AUTHORIZATION, "Bearer " + "")
//			      .get("http://localhost:8081/resource-server-jwt/foos");
//
//		TimeUtils.before().day(1);
    }

}
