package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.entity.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
	
	@Query(value = "select * from App_Role t where t.userId = :userId", nativeQuery = true)
	List<String> getRoleNames(Long userId);
	
}
