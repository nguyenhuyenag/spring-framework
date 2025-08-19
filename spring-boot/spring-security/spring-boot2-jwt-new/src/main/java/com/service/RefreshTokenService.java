package com.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.RefreshToken;
import com.entity.User;
import com.payload.response.TokenRefreshResponse;
import com.repository.RefreshTokenRepository;
import com.repository.UserRepository;
import com.util.TokenHandler;

@Service
public class RefreshTokenService {

	private final long refreshTokenDurationMs = TimeUnit.DAYS.toMillis(1);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	private boolean verifyJwtExpiration(String token) {
		Optional<Integer> verify = refreshTokenRepository.verifyJwtExpiration(token);
        return verify.filter(x -> x == 1).isPresent();
    }

	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUsername(username);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public TokenRefreshResponse refreshToken(String refresh_token) {
		TokenRefreshResponse response = new TokenRefreshResponse();
		// validate token
		boolean verify = verifyJwtExpiration(refresh_token);
		if (!verify) {
			response.setMessage("Token is expiration or not in database!");
		} else {
			RefreshToken refreshToken = refreshTokenRepository.findByToken(refresh_token);
			String username = refreshToken.getUsername();
			User user = userRepository.findByUsername(username);
			if (user == null) {
				response.setMessage("Token information is incorrect!");
			} else {
				String jwt = TokenHandler.createJWT(username, user.getStringAuthorities());
				response.setType("Bearer");
				response.setAccessToken(jwt);
			}
		}
		response.setRefreshToken(refresh_token);
		return response;
	}

	// @Transactional
	// public int deleteByUserId(Long userId) {
	// return
	// refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
	// }

	// public RefreshToken verifyExpiration(RefreshToken token) {
	// if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
	// refreshTokenRepository.delete(token);
	// throw new TokenRefreshException(token.getToken(),
	// "Refresh token was expired. Please make a new signin request");
	// }
	// return token;
	// }

}
