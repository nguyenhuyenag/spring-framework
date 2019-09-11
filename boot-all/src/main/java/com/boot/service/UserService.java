package com.boot.service;

import java.util.List;

import com.boot.entity.User;
import com.boot.request.RegisterRequest;

public interface UserService {

	List<User> loadAll();

	void register(RegisterRequest dto);

}
