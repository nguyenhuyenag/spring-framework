package com.soap;

import javax.jws.WebService;

@WebService(endpointInterface = "com.soap.HelloWorldService")
public class HelloWorldServiceImpl implements HelloWorldService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name + "!";
	}

}