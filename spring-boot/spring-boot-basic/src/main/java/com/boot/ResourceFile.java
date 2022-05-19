package com.boot;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ResourceFile {

	private static final Logger LOG = LoggerFactory.getLogger(ResourceFile.class);

	public static File byClassPathResource() {
		try {
			Resource resource = new ClassPathResource("data/resource-data.txt");
			if (!resource.exists()) {
				LOG.error("File not found!");
				return null;
			}
			// File file = resource.getFile();
			InputStream is = resource.getInputStream();
			System.out.println(is.toString());
			// System.out.println(resource.getDescription());
			System.out.println(resource.getFilename());
			// System.out.println(resource.getURI().toASCIIString());
			System.out.println(resource.getURL().toString());
			System.out.println(resource.getFile().toString());
			// resource.getFilename();
			return resource.getFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void readPropertiesToMap() {
		try {
			PropertiesFactoryBean factory = new PropertiesFactoryBean();
			factory.setLocation(new ClassPathResource("basic.properties"));
			factory.afterPropertiesSet();
			factory.getObject().forEach((k, v) -> System.out.println(k + ", " + v));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
