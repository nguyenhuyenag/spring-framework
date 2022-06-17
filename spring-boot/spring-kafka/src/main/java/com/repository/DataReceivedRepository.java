package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.DataReceived;

@Repository
public interface DataReceivedRepository extends JpaRepository<DataReceived, Long> {

	boolean existsByDataCode(String code);

}
