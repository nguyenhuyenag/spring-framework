package com.configuration;

import com.entity.User;
import com.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
// @Slf4j
public class ApplicationInit {

//    private PasswordEncoder passwordEncoder;
//
//    @Bean
//    public ApplicationRunner applicationRunner(UserRepository userRepository) {
//        return args -> {
//            Optional<User> admin = userRepository.findByUsername("admin");
//            if (admin.isPresent()) {
//                var roles = new HashSet<String>();
//                roles.add(Role.ADMIN.name());
//
//                User user = User.builder()
//                        .username("admin")
//                        .password(passwordEncoder.encode("admin"))
//                        // .roles(roles)
//                        .build();
//
//                userRepository.save(user);
//                log.warn("admin user has been created with default password: admin, please change it");
//            }
//        };
//    }

}
