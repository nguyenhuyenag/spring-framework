package com.test.client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class ClientController {

	@Autowired
	private EurekaClient eurekaClient;

	@Autowired
	private RestTemplateBuilder builder;

	@RequestMapping("")
	public String callService() {
		InstanceInfo instance = eurekaClient.getNextServerFromEureka("service", false);
		String homePageUrl = instance.getHomePageUrl();
		RestTemplate restTemplate = builder.build();
		ResponseEntity<String> exchange = restTemplate.exchange(homePageUrl, HttpMethod.GET, null, String.class);
		return exchange.getBody();
	}

}
