package com.auth;

import com.entity.User;
import com.repository.UserRepository;
import com.util.RequestUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
// @NoArgsConstructor(force = true)
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final UserRepository repository;
    // private final LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        System.out.println("[" + this.getClass().getSimpleName() + "] Exception: " + exception.getMessage());

        String email = request.getParameter("username");
        Optional<User> opt = repository.findByUsername(email);
        if (opt.isEmpty()) {
            // exception = handleError(opt.get());
            log.error("User `{}` not found", email);
        }

        // Login failed by BadCredentialsException (username or password incorrect)
        if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
            log.info("IP: {}", RequestUtils.getClientIPAddress(request));
            // loginAttemptService.loginFailed(RequestUtils.getClientIPAddress(request));
        }

        super.setDefaultFailureUrl("/login?error=true");
        super.onAuthenticationFailure(request, response, exception);
    }

    // Set exception
//	private AuthenticationException handleError(User user) {
//		if (user == null) {
//			return new UsernameNotFoundException("Username_Not_Found_Exception");
//		}
//		if (user.getEnabled() == 0) {
//			return new DisabledException("Disabled_Exception");
//		}
//		return new BadCredentialsException("Bad_Credentials_Exception");
//	}

}
