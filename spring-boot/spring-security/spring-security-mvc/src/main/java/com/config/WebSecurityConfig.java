package com.config;

import com.auth.CustomLogoutHandler;
import com.auth.CustomLoginFailureHandler;
import com.auth.CustomLoginSuccessHandler;
import com.util.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity ( //
//	prePostEnabled = true, 	 // @PreAuthorize, @PostAuthorize
//	securedEnabled = true, 	 // @Secured
//	jsr250Enabled = true	 // @RolesAllowed
//)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final CustomLoginSuccessHandler loginSuccessHandler;
    private final CustomLoginFailureHandler loginFailureHandler;
    private final CustomLogoutHandler logoutHandler;

    // @formatter:off
    private final String[] AUTH_WHITELIST = {
        "/static/**", "/login*", "/logout", "/favicon.ico"
    };
    // @formatter:on

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> request
                .antMatchers(AUTH_WHITELIST).permitAll() // Public URL
                .antMatchers("/admin").hasAuthority(Roles.ROLE_ADMIN.name())
                // .antMatchers("/auth/admin/*").hasAuthority("ADMIN")
                // .antMatchers("/auth/*").hasAnyAuthority("ADMIN", "USER")
                .anyRequest().authenticated());

        http.authorizeRequests(withDefaults())
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginProcessingUrl("/j_spring_security_check") // The URL to submit the username and password to
                        .successHandler(loginSuccessHandler)
                        .failureUrl("/login?error=true")
                        .failureHandler(loginFailureHandler))
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Csrf logout
                        .logoutSuccessHandler(logoutHandler)
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));

        http.authorizeRequests(withDefaults())
                .exceptionHandling(handling -> handling.accessDeniedPage("/403"));

        http.rememberMe(remember -> {
            int millis = (int) TimeUnit.DAYS.toSeconds(1);
            remember.key("secretAndUnique")
                    .rememberMeParameter("rememberMe") // Name of checkbox at login page
                    .rememberMeCookieName("remember-me-name")
                    .tokenValiditySeconds(millis);
        });

        http.headers(header -> header.addHeaderWriter( //
                new ClearSiteDataHeaderWriter(Directive.CACHE, Directive.COOKIES, Directive.STORAGE)));

        // http.sessionManagement(management -> management
        //     .sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        // http.sessionManagement().maximumSessions(1); // Limit login (thiết bị đăng nhập)
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)    // Cài đặt dịch vụ để tìm kiếm User trong Database
                .passwordEncoder(passwordEncoder());    // Cài đặt PasswordEncoder
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
