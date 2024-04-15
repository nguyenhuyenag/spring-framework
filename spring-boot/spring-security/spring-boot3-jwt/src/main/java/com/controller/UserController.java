package com.controller;

import com.dto.request.ApiResponse;
import com.dto.request.UserCreationRequest;
import com.dto.response.UserCreationResponse;
import com.entity.User;
import com.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @PostMapping // ("/create")
    ApiResponse<?> createUser(@RequestBody UserCreationRequest request) {
        System.out.println("request = " + request);
        UserCreationResponse user = userService.createUser(request);
        return ApiResponse.<UserCreationResponse>builder()
                .result(user)
                .build();
    }

}
