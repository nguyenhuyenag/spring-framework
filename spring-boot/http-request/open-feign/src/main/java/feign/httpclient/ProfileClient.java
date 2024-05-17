package feign.httpclient;

import feign.configuration.AuthenticationRequestInterceptor;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = { AuthenticationRequestInterceptor.class })
public interface ProfileClient {

    // @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    // ApiResponse<UserProfileResponse> createProfile(@RequestBody ProfileCreationRequest request);

}
