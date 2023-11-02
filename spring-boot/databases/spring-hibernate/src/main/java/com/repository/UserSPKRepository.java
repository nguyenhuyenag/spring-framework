package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.onetoone.withSharedPrimaryKey.UserSPK;

public interface UserSPKRepository extends JpaRepository<UserSPK, Integer> {

}
