package com.service;

import com.dto.response.UserResponse;
import com.entity.User;
import com.mapper.UserMapper;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class UserService {

    private UserMapper userMapper;
    private UserRepository userRepository;

    public UserResponse getUserById(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toUserResponse(user);
    }

}
