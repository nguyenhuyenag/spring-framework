package com.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByUsername(String username);

	@Query(value = "SELECT t1.role_name " //
			+ "FROM role t1 " //
			+ "JOIN user_role t2 ON t1.role_id = t2.role_id " //
			+ "WHERE t2.user_id = :userId", nativeQuery = true)
	List<String> getListRolesByUserId(int userId);

	@Modifying
	@Transactional
	@Query(value = "UPDATE user t SET " //
			+ "t.username = :newUsername, t.password = :password " //
			+ "WHERE t.username = :oldUsername", nativeQuery = true)
	void updateUsernameAndPassword(String oldUsername, String newUsername, String password);

}
