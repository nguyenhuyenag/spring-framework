package com.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.entity.Clazz;

public interface JpaDateTimeRepository extends JpaRepository<Clazz, Integer> {

	// 2023-08-22 08:40:29.0
	@Query(value = "SELECT NOW()", nativeQuery = true)
	java.util.Date getAsDate();

	// 2023-08-22T08:40:51
	@Query(value = "SELECT NOW()", nativeQuery = true)
	LocalDateTime getAsLocalDateTime();

	// 2023-08-22
	@Query(value = "SELECT CURDATE()", nativeQuery = true)
	LocalDate getAsLocalDate();

	// 20230822
	@Query(value = "SELECT CURDATE() + 0", nativeQuery = true)
	String getAsLocalDateString();

	// 2023-08-22
	@Query(value = "SELECT CURDATE()", nativeQuery = true)
	java.sql.Date getAsDateSQL();

	// 08:45:56
	@Query(value = "SELECT CURTIME()", nativeQuery = true)
	java.sql.Time getAsTime();

}
