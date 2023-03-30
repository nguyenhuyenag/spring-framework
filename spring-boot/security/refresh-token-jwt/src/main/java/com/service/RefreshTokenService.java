package com.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.RefreshToken;
import com.payload.reponse.TokenRefreshResponse;
import com.payload.request.TokenRefreshRequest;
import com.repository.RefreshTokenRepository;
import com.util.TokenHandler;

@Service
public class RefreshTokenService {

	private Long refreshTokenDurationMs = TimeUnit.DAYS.toMillis(1);

	@Autowired
	private UserService userService;

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	// @Autowired
	// private UserRepository userRepository;

	// public Optional<RefreshToken> findByToken(String token) {
	// return refreshTokenRepository.findByToken(token);
	// }

	private boolean verifyExpiration(String token) {
		Optional<Integer> verify = refreshTokenRepository.verifyExpiration(token);
		if (verify.isPresent()) {
			return verify.get() == 1;
		}
		return false;
	}

	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUsername(username);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public TokenRefreshResponse refreshToken(TokenRefreshRequest request) {
		TokenRefreshResponse response = new TokenRefreshResponse();
		String refreshToken = request.getRefreshToken();
		// validate token
		boolean verify = verifyExpiration(refreshToken);
		if (!verify) {
			response.setMessage("Refresh token is not in database or expiration!");
		} else {
			RefreshToken findByToken = refreshTokenRepository.findByToken(refreshToken);
			String username = findByToken.getUsername();
			String authorities = userService.findAuthoritiesByUsername(username);
			String jwtToken = TokenHandler.createJWT(username, authorities);
			response.setAccessToken(jwtToken);
		}
		response.setRefreshToken(refreshToken);
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
