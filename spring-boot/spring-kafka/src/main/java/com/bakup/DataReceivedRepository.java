package com.bakup;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DataReceivedRepository extends JpaRepository<DataReceived, Long> {

	boolean existsByDataCode(String code);

}
