package com.service;

import com.entity.User;

public interface UserService {

	User findByEmail(String email);

}
