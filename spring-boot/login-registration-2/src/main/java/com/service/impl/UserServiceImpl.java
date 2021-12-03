package com.service.impl;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.service.UserService;
import com.util.ListMessage;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private UserRepository repository;

	@Override
	public String changePassword(String username, String password, String newPassword, String nhaplaimatkhaumoi) {
		String message = validate(username, password, newPassword, nhaplaimatkhaumoi);
		if (StringUtils.isNotEmpty(message)) {
			return message;
		}
		repository.changePassword(username, encoder.encode(newPassword));
		return "OK";
	}

	private String validate(String username, String password, String newPassword, String repeatPassword) {
		Optional<User> opt = repository.findByUsername(username);
		if (opt.isPresent()) {
			User user = opt.get();
			if (!encoder.matches(password, user.getPassword())) {
				return ListMessage.PWD_WRONG;
			}
		}
		if (StringUtils.isEmpty(newPassword)) {
			return StringUtils.replace(ListMessage.FIELD_REQUIED, "%s", "mật khẩu mới");
		}
		if (StringUtils.isEmpty(repeatPassword)) {
			return StringUtils.replace(ListMessage.FIELD_REQUIED, "%s", "lại mật khẩu mới");
		}
		if (password.equals(newPassword)) {
			return ListMessage.PWD_DUPLICATE;
		}
		if (!newPassword.equals(repeatPassword) ) {
			return ListMessage.PWD_REPEAT_WRONG;
		}
		if (newPassword.length() < 6) {
			return ListMessage.PWD_LENGTH_MIN;
		}
		return "";
	}

}
