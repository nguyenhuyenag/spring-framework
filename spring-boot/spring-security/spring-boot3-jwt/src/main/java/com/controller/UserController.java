package com.controller;

import com.dto.request.ApiResponse;
import com.dto.request.UserCreationRequest;
import com.dto.request.UserUpdateRequest;
import com.dto.response.UserResponse;
import com.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @PostMapping
    public ApiResponse<?> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse user = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .build();
    }

    @GetMapping("/who-i-am")
    public ApiResponse<?> whoIam() {
        getAuthenticationInfo();
        UserResponse result = userService.whoIam();
        return ApiResponse.<UserResponse>builder()
                .result(result)
                .build();
    }

    // Get token from request
    public String extractToken() {
        ServletRequestAttributes servletRequestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
    }

    // Get token from Authentication
    public void getAuthenticationInfo() {
        // Lấy thông tin của user đang được auth trong request hiện tại
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        // Get username, roles,...
        LOG.info("The authenticates using the Token created by the user:");
        LOG.info("Username: {}", auth.getName());
        LOG.info("Roles: {}", Arrays.toString(auth.getAuthorities().toArray()));

        // Get token
        if (auth instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String token = jwt.getTokenValue();
            LOG.info("Token: {}", token);
            LOG.info("Header: {}", extractToken());
        }
    }

    @GetMapping("/{userId}")
    public ApiResponse<?> getUser(@PathVariable("userId") String userId) {
        UserResponse user = userService.getUserById(userId);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .build();
    }

    @GetMapping
    public ApiResponse<?> getUsers() {
        List<UserResponse> users = userService.getUsers();
        return ApiResponse.builder()
                .result(users)
                .build();
    }

    @PutMapping("/{userId}")
    public ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(userId, request))
                .build();
    }

}
