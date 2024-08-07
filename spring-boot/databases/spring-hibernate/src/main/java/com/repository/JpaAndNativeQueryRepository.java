package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.entity.id.idclass.User;
import com.entity.id.idclass.UserId;

public interface JpaAndNativeQueryRepository extends JpaRepository<User, UserId> {

	@Query(value = "SELECT u FROM User u WHERE u.id = :id AND u.code = :code")
	User getByJpaQuery(@Param("id") int id, @Param("code") int code);

	@Query(value = "SELECT s.* FROM student s WHERE s.id = :id AND s.code = :code", nativeQuery = true)
	User getByNativeQuery(@Param("id") int id, @Param("code") int code);

}
