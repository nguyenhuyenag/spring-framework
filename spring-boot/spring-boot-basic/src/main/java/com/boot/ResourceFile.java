package com.boot;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ResourceFile {

	public static File byClassPathResource() {
		try {
			Resource resource = new ClassPathResource("data/resource-data.txt");
			// return resource.getInputStream();
			return resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
