package com.configuration;

import com.entity.User;
import com.enums.Role;
import com.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
// @RequiredArgsConstructor
// @FieldDefaults(makeFinal = true)
// @Slf4j
public class ApplicationInit {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner applicationRunner(UserRepository userRepository) {
        return args -> {
            Optional<User> admin = userRepository.findByUsername("admin");
            if (admin.isEmpty()) {
                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("123456"))
                        .roles(Set.of(Role.ADMIN.name()))
                        .build();
                userRepository.save(user);
                // log.warn("Admin user has been created with default password: admin, please change it");
            }
        };
    }

}
