package com.of.component.life.cycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycle {

	@PostConstruct
	public void postConstruct() {
		System.out.println("Call PostConstruct ...");
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("Call PreDestroy ...");
	}

}
