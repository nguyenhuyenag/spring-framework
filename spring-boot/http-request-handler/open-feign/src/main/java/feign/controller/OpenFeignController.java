package feign.controller;

import feign.FeignException;
import feign.dto.LoginRequest;
import feign.dto.LoginResponse;
import feign.dto.Profile;
import feign.dto.User;
import feign.httpclient.UserAuthClient;
import feign.httpclient.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenFeignController {

    private final UserClient userClient;
    private final UserAuthClient userAuthClient;

    @GetMapping("/users")
    public List<User> getUsers() {
        return userClient.getAllUsers();
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userClient.login(request);
    }

    @GetMapping("/auth/profile")
    public Profile profile() {
        try {
            Profile profile = userAuthClient.profile();
            return profile;
        } catch (FeignException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
