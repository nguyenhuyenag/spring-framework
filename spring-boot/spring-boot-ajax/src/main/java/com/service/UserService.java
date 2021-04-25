package com.service;

import org.springframework.stereotype.Service;

import com.model.User;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	private List<User> list = new ArrayList<>();

	// init data
	@PostConstruct
	private void iniDataForTesting() {
		list.add(new User("abc", "abc_111", "abc@abc.com"));
		list.add(new User("google", "gl_222", "gl@google.com"));
		list.add(new User("dell", "del@#123", "pc@dell.com"));
	}

	public List<User> findByUserNameOrEmail(String username) {
		return list.stream() //
				.filter(t -> t.getUsername().equalsIgnoreCase(username)) //
				.collect(Collectors.toList());
	}
}
