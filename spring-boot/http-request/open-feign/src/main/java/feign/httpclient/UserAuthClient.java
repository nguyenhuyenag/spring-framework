package feign.httpclient;

import feign.configuration.AuthenticationRequestInterceptor;
import feign.dto.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-auth-client", url = "${app.services.profile}",
        configuration = AuthenticationRequestInterceptor.class)
public interface UserAuthClient {

    @GetMapping("/auth/profile")
    Profile profile();

}
