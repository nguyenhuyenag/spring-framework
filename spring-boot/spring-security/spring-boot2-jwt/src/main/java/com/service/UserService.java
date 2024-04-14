package com.service;

import java.util.List;

import com.exception.AppException;
import com.exception.ErrorCode;
import com.payload.request.UserCreationRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder encoder;

	public User create(UserCreationRequest request) {
		User user = new User();
		if (userRepository.existsByUsername(request.getUsername())) {
			// Ném ra Exception + message sau đó dùng @ControllerAdvice để xử lý
			// throw new RuntimeException("User exist");
			throw new AppException(ErrorCode.USER_EXIST);
		}
		BeanUtils.copyProperties(request, user);
		user.setPassword(encoder.encode(request.getPassword()));
		return userRepository.save(user);
	}

	public List<User> loadAll() {
		return userRepository.getAllUser();
	}

//	public String findAuthoritiesByUsername(String username) {
//		StringJoiner joiner = new StringJoiner(",");
//		Optional<User> opt = userRepository.findByUsername(username);
//		if (opt.isPresent()) {
//			opt.get().getRoles().forEach(t -> joiner.add("ROLE_" + t.getName()));
//		}
//		return joiner.toString();
//	}

}
