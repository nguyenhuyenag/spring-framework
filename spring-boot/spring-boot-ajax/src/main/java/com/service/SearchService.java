package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.model.User;

@Service
public class SearchService {

	private List<User> list = new ArrayList<>();

	// init data
	@PostConstruct
	private void iniDataForTesting() {
		list.add(new User("Google", "gl@gmail.com"));
		list.add(new User("Yahoo", "yh@yahoo.com"));
		list.add(new User("Dell", "pc@dell.com"));
		list.add(new User("Amazon", "amz@amz.com"));
	}

	public List<User> findByUserNameOrEmail(String username) {
		return list.stream() //
				.filter(t -> t.getName().equalsIgnoreCase(username)) //
				.collect(Collectors.toList());
	}
}
