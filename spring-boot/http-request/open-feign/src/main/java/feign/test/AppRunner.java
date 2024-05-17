package feign.test;

import feign.httpclient.BeerClient;
import feign.httpclient.JSONPlaceHolderClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class AppRunner implements ApplicationRunner {

    private final BeerClient beerClient;
    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // var posts = jsonPlaceHolderClient.getPosts();
        // posts.forEach(p -> System.out.println(p.getTitle()));

//        var post = jsonPlaceHolderClient.getPostById(1L);
//        if (post != null) {
//            System.out.println(post);
//        }
    }

}
