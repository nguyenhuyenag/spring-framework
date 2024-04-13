package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.RefreshToken;

// @Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	RefreshToken findByToken(String token);
	
	@Query(value = 
		"SELECT EXISTS (" + 
			"SELECT t.token FROM refresh_token t " + 
			"WHERE t.token = :token " + 
			"AND (t.expiry_date >= NOW() OR t.expiry_date IS NULL)" + 
		")", nativeQuery = true)
	Optional<Integer> verifyJwtExpiration(String token);

}
