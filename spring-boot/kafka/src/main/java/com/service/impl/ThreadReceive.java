package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.model.Message;
import com.repository.MessageRepository;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@Component
public class ThreadReceive implements Runnable {

	@Autowired
	private MessageRepository repository;

	private String content;
	
//	@Autowired
//	public ThreadReceive(MessageRepository repository) {
//		this.repository = repository;
//	}

	public ThreadReceive(String content) {
		this.content = content;
	}

	@Override
	public void run() {
		repository.save(new Message(content));
		System.out.println("OK");
	}

}
