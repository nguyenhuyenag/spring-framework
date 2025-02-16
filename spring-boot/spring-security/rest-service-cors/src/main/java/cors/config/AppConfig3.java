package cors.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig3 implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                // .allowedMethods("*")
                .allowedOrigins("http://127.0.0.1:5500/")
                .allowedMethods("POST", "GET")
                .allowedHeaders("*");
    }

}
