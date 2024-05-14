package com.controller;

import com.dto.response.UserResponse;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    // http://localhost:8080/users/5cc28321-16ef-465f-8860-9163bc5c88c2
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") String userId) {
        // getAuthenticationInfo();
        UserResponse user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

}
