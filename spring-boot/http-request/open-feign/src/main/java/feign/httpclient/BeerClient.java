package feign.httpclient;

import feign.configuration.AuthenticationRequestInterceptor;
import feign.response.Beer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "beer-service", url = "${app.services.profile}",
        configuration = AuthenticationRequestInterceptor.class)
public interface BeerClient {

    @GetMapping(value = "/beers", produces = MediaType.APPLICATION_JSON_VALUE)
    Beer getBeer();

}
