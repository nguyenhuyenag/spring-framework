package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

	RefreshToken findByToken(String token);
	
	@Query(value = "SELECT NOW() <= t.expiry_date "
			+ "FROM refresh_token t WHERE t.token = :token", nativeQuery = true)
	Optional<Integer> verifyExpiration(String token);

}
