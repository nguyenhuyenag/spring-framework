package com.configuration;

import com.exception.JwtAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {"/auth/**", "/users"};

    // @Value("${jwt.signerKey}")
    // private String signerKey;

    // @Autowired
    // private CustomJwtDecoder customJwtDecoder;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);

        // @formatter:off
        http.authorizeHttpRequests(req ->
                req.requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS)
                        .permitAll()
                   .anyRequest()
                   .authenticated()
        );

        http.oauth2ResourceServer(oauth2 ->
                oauth2.jwt(jwtConfigurer ->
                        jwtConfigurer.decoder(jwtDecoder())
                                     .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )
                // Điểm mà authentication faild -> Sẽ làm gì đó
                .authenticationEntryPoint(new JwtAuthenticationEntryPoint())
        );
        // @formatter:on

        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        JwtGrantedAuthoritiesConverter grantConverter = new JwtGrantedAuthoritiesConverter();

        // (1) Thay đổi prefix, mặc định là 'SCOPE_XXX'
        grantConverter.setAuthorityPrefix("");

        // (2) Thay đổi dấu phân cách giữa các role, mặc định là khoảng trắng
        // grantConverter.setAuthoritiesClaimDelimiter();

        // (3) Thay đổi {"scope": "ADMIN"} -> {"roles": "ADMIN"}
        // grantConverter.setAuthoritiesClaimName("roles");

        converter.setJwtGrantedAuthoritiesConverter(grantConverter);
        return converter;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS512)
//                .build();
        return new CustomJwtDecoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    // @formatter:off
//    public void method() {
//        int a=1;    String b="hello"; // This line will not be formatted
//    }
//    // @formatter:on

}
