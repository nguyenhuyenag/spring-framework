package com.boot;

import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

public class SpringInformation {

	public static void version() {
		System.out.println("Spring version: " + SpringVersion.getVersion());
		System.out.println("Spring Boot version: " + SpringBootVersion.getVersion());
		System.out.println("Hibernate version: " + org.hibernate.Version.getVersionString());
	}

	public static void main(String[] args) {
		version();
	}

}
