package com.service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.RefreshToken;
import com.exception.TokenRefreshException;
import com.repository.RefreshTokenRepository;

@Service
public class RefreshTokenService {

	// @Value("${bezkoder.app.jwtRefreshExpirationMs}")
	private Long refreshTokenDurationMs = TimeUnit.HOURS.toMillis(1);

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	// @Autowired
	// private UserRepository userRepository;

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = new RefreshToken();
		refreshToken.setUsername(username);
		refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
		refreshToken.setToken(UUID.randomUUID().toString());
		refreshToken = refreshTokenRepository.save(refreshToken);
		return refreshToken;
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new TokenRefreshException(token.getToken(),
					"Refresh token was expired. Please make a new signin request");
		}

		return token;
	}

//	@Transactional
//	public int deleteByUserId(Long userId) {
//		return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
//	}

}
