package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableJpaAuditing
@SpringBootApplication
public class SpringHibernateApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringHibernateApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}
