package com.controller;

import com.dto.request.ApiResponse;
import com.dto.request.UserCreationRequest;
import com.dto.response.UserResponse;
import com.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping("create")
    public ApiResponse<?> createUser(@RequestBody @Valid UserCreationRequest request) {
        UserResponse user = userService.createUser(request);
        return ApiResponse.<UserResponse>builder()
                .result(user)
                .build();
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

}
