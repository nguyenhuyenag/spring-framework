package feign.httpclient;

import feign.dto.LoginRequest;
import feign.dto.LoginResponse;
import feign.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-client", url = "${app.services.profile}")
public interface UserClient {

    @GetMapping("/users")
    List<User> getAllUsers();

    @PostMapping("/auth/login")
    LoginResponse login(@RequestBody LoginRequest request);

}
