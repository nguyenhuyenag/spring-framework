package feign.controller;

import feign.httpclient.BeerClient;
import feign.response.Beer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenFeignController {

    private final BeerClient beerClient;

    @GetMapping("/beer")
    public Beer getBeer() {
        return beerClient.getBeer();
    }

}
