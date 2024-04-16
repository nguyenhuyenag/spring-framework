package com.service;

import com.dto.request.UserCreationRequest;
import com.dto.response.UserResponse;
import com.entity.User;
import com.enums.ErrorCode;
import com.enums.Role;
import com.exception.AppException;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*-
    @FieldDefaults(makeFinal = true): Đánh dấu tất cả các field là final (trừ field được đánh dấu @NonFinal)
    @RequiredArgsConstructor: Constructor này sẽ DI cho UserRepository ở bên dưới (những field final)
 */
@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);
        // Encode password
        user.setPassword(encoder.encode(request.getPassword()));
        // Add default role
        Set<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        User entity = userRepository.save(user);

        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public UserResponse getUserById(String userId) {
        // User user = userRepository.findUserById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        UserResponse response = new UserResponse();
        BeanUtils.copyProperties(user, response);
        return response;
    }

    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> result = new ArrayList<>();
        users.forEach(u -> {
            UserResponse response = new UserResponse();
            BeanUtils.copyProperties(u, response);
            result.add(response);
        });
        return result;
    }

}
