package com.service;

import com.dto.request.UserCreationRequest;
import com.dto.request.UserUpdateRequest;
import com.dto.response.UserResponse;
import com.entity.Role;
import com.entity.User;
import com.enums.ErrorCode;
import com.exception.AppException;
import com.mapper.UserMapper;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    public UserResponse createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new AppException(ErrorCode.USER_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(request, user);

        // Encode password
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        // Add default role
        Set<Role> roles = Set.of(roleRepository.findByName("USER"));
        user.setRoles(roles);

        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

    /**
     * Kiểm tra user được xác thực có phải là user cần thao tác lấy dữ liệu.
     * Để tránh trường hợp token của user A dùng cho user B
     */
    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {
        // .orElseThrow(() -> new RuntimeException("User not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // UserResponse response = new UserResponse();
        // BeanUtils.copyProperties(user, response);

        // response.setRoles(user.getRoles());

        return userMapper.toUserResponse(user);
    }

    /*-
        @PreAuthorize  -> Kiểm tra trước khi gọi hàm (không có log)
        @PostAuthorize -> Kiểm tra sau khi gọi hàm chạy xong (có dòng log)
     */
    @PreAuthorize("hasRole('ADMIN')") // = ROLE_ADMIN
    // @PreAuthorize("hasAuthority('APPROVE_POST')") // = APPROVE_POST
    public List<UserResponse> getUsers() {
        LOG.info("In getUsers method");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse).toList();
    }

    public UserResponse whoIam() {
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        // UserResponse response = new UserResponse();
        // BeanUtils.copyProperties(user, response);
        return userMapper.toUserResponse(user);
    }

//    public UserResponse updateUser(String userId, UserUpdateRequest request) {
//        // Get user from db by userId
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
//
//        // userMapper.updateUser(user, request);
//        BeanUtils.copyProperties(request, user);
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//
//        var roles = roleRepository.findAllById(request.getRoles());
//        user.setRoles(new HashSet<>(roles));
//
//        var response = new UserResponse();
//        user = userRepository.save(user);
//        BeanUtils.copyProperties(user, response);
//        // Convert Set<Role> -> Set<RoleResponse>
//        Set<RoleResponse> responseSet = new HashSet<>();
//        user.getRoles().forEach(r -> {
//            RoleResponse roleResponse = new RoleResponse();
//            BeanUtils.copyProperties(r, roleResponse);
//            responseSet.add(roleResponse);
//        });
//        response.setRoles(responseSet);
//        return response;
//    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        user = userRepository.save(user);
        return userMapper.toUserResponse(user);
    }

}
