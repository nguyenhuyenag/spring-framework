package com.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.util.LoginAttemptService;
import com.util.RequestUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserRepository repository;
    private final HttpServletRequest request;
    private final LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String ip = RequestUtils.getClientIPAddress(request);
        if (loginAttemptService.isBlocked(ip)) {
            throw new BadCredentialsException("BLOCK_IP");
        }

        Optional<User> optionalUser = repository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            log.info("User `{}` was not found!", username);
            throw new UsernameNotFoundException("User `" + username + "` was not found!");
        }

        User user = optionalUser.get();
        if (user.isDisabled()) {
            log.info("User `{}` is disabled", username);
            // throw new BadCredentialsException("USER_DISABLED");
        }

        // [ROLE_USER, ROLE_ADMIN, ...]
        List<GrantedAuthority> roles = userService.getGrantedAuthorityByUserId(user.getUserId());
        log.info("Roles of `{}`: {}", user.getUsername(), roles);
        return org.springframework.security.core.userdetails.User //
                .withUsername(user.getUsername()) //
                .password(user.getPassword()) ///
                .disabled(user.isDisabled()) //
                // .accountLocked(true)
                .authorities(roles) //
                .build();
    }

}
