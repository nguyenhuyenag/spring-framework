package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.model.User;

@Service
public class UserService {

	private static List<User> list = new ArrayList<>();

	public void create() {
		list.clear();
		list.add(new User("Google", "gl@gmail.com"));
		list.add(new User("Yahoo", "yh@yahoo.com"));
		list.add(new User("Oracle", "orc@oracle.com"));
		list.add(new User("Amazon", "amz@amz.com"));
	}

	@PostConstruct
	public void init() {
		create();
	}

	public List<User> getListUser() {
		return list;
	}

//	@Override
//	public UserDto getUserById(int id) {
//		Optional<User> user = userRepository.findById(id);
//		if (!user.isPresent()) {
//			throw new NotFoundException("No user found");
//		}
//
//		return UserMapper.toUserDto(user.get());
//	}

//	@Override
//	public UserDto createUser(CreateUserReq req) {
//		// Check email exist
//		User user = userRepository.findByEmail(req.getEmail());
//		if (user != null) {
//			throw new DuplicateRecordException("Email is already in use");
//		}
//
//		// user = UserMapper.toUser(req);
//		userRepository.save(user);
//
//		return null; // UserMapper.toUserDto(user);
//	}
//
//	@Override
//	public UserDto updateUser(UpdateUserReq req, int id) {
//		Optional<User> user = userRepository.findById(id);
//		if (!user.isPresent()) {
//			throw new NotFoundException("No user found");
//		}
//
//		User updateUser = null; // UserMapper.toUser(req, id);
//		try {
//			userRepository.save(updateUser);
//		} catch (Exception ex) {
//			throw new InternalServerException("Database error. Can't update user");
//		}
//
//		return null; // UserMapper.toUserDto(updateUser);
//	}
//
//	@Override
//	public void deleteUser(int id) {
//		Optional<User> user = userRepository.findById(id);
//		if (!user.isPresent()) {
//			throw new NotFoundException("No user found");
//		}
//
//		try {
//			userRepository.deleteById(id);
//		} catch (Exception ex) {
//			throw new InternalServerException("Database error. Can't delete user");
//		}
//	}
//
//	@Override
//	public UserDto getUserById(int id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
