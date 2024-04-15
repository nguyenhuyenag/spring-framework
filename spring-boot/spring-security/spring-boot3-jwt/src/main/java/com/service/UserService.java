package com.service;

import com.dto.request.UserCreationRequest;
import com.dto.response.UserResponse;
import com.entity.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
// Constructor này sẽ DI UserRepository ở bên dưới
@RequiredArgsConstructor
// Đánh dấu tất cả các field là final (trừ field được đánh dấu @NonFinal)
@FieldDefaults(makeFinal = true)
public class UserService {

    private UserRepository userRepository;

    public UserResponse createUser(UserCreationRequest request) {
        User user = new User();
        BeanUtils.copyProperties(request, user);
        User entity = userRepository.save(user);
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public UserResponse getUserById(String userId) {
        User user = userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

}
