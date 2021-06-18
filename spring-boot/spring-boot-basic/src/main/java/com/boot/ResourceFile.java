package com.boot;

import java.io.File;
import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

// @Component
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

//	@Autowired
//	private ResourceLoader tFoo;
//
//	private static ResourceLoader foo;
//
//	@PostConstruct
//	public void init() {
//		ResourceFile.foo = tFoo;
//	}
//
//	public static File byResourceLoader() {
//		try {
//			Resource resource = foo.getResource("data/resource-data.txt");
//			// return resource.getInputStream();
//			return resource.getFile();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//
////		Resource resource = foo.getResource("classpath:android.png");
////
////	    InputStream input = resource.getInputStream();
////
////	    File file = resource.getFile();
//	}

}
