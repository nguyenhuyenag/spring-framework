package com.repository.primary;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.primary.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public Optional<User> findByUsername(String username);
	
	@Query("select CURRENT_TIMESTAMP from User")
	public Date getTimeStamp();
	
	public User findByEmail(String email);
	
}
