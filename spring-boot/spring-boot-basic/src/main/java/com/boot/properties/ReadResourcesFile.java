package com.boot.properties;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *	https://www.baeldung.com/spring-classpath-file-access
 */
public class ReadResourcesFile {

	public static void main(String[] args) throws IOException {
		Resource resource = new ClassPathResource("data/resource-data.txt");
		if (resource.exists()) {
			System.out.println(resource.getFile().toString());
		}
	}

}
