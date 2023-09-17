package com.auth;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.entity.User;
import com.repository.UserRepository;
import com.service.LoginAttemptService;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger LOG = LoggerFactory.getLogger(CustomLoginFailureHandler.class);

	@Autowired
	private UserRepository repository;

	@Autowired
	private LoginAttemptService loginAttemptService;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

		System.out.println("[" + this.getClass().getSimpleName() + "] Exception: " + exception.getMessage());
		
		String email = request.getParameter("username");
		Optional<User> opt = repository.findByUsername(email);
		
		if (opt.isPresent()) {
			// exception = handleError(opt.get());
		}
		
		// String email = request.getParameter("email");
        User user = opt.get(); //  loginAttemptService.getByEmail(email);
         
        if (user != null) {
            if (user.isEnabled() && user.isAccountNonLocked()) {
                if (user.getFailedAttempt() < LoginAttemptService.MAX_FAILED_ATTEMPTS - 1) {
                	loginAttemptService.increaseFailedAttempts(user);
                } else {
                	loginAttemptService.lock(user);
                    exception = new LockedException("Your account has been locked due to 3 failed attempts."
                            + " It will be unlocked after 24 hours.");
                }
            } else if (!user.isAccountNonLocked()) {
                if (loginAttemptService.unlockWhenTimeExpired(user)) {
                    exception = new LockedException("Your account has been unlocked. Please try to login again.");
                }
            }
             
        }
		
		// Login failed by BadCredentialsException (username or password incorrect)
//		if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
//			LOG.info("IP: {}", RequestUtils.getClientIPAddress(request));
//			loginAttemptService.loginFailed(RequestUtils.getClientIPAddress(request));
//		}

		super.setDefaultFailureUrl("/login?error=true");
		super.onAuthenticationFailure(request, response, exception);
	}

	// Set exception
	private AuthenticationException handleError(User user) {
		if (user == null) {
			return new UsernameNotFoundException("Username_Not_Found_Exception");
		}
//		if (user.getEnabled() == 0) {
//			return new DisabledException("Disabled_Exception");
//		}
		return new BadCredentialsException("Bad_Credentials_Exception");
	}

}
