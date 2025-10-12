package com.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST = {
            "/", "/css/**", "/script/**", "/api/**", "/login"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // KHÔNG cần csrf(enable) — mặc định đã bật rồi
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")  // trang login tùy chỉnh
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(request -> {
                            // Cho phép GET hoặc POST tới /logout
                            return request.getRequestURI().equals("/logout") &&
                                    ("GET".equals(request.getMethod()) || "POST".equals(request.getMethod()));
                        })
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );


        return http.build();
    }

    /**
     * 👤 User tạm trong bộ nhớ (InMemory)
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123456"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

    /**
     * 🔑 Bộ mã hóa mật khẩu (bắt buộc từ Spring Security 5+)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
