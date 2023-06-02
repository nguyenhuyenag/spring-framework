package com.second.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.second.entity.Office;

public interface OfficeRepository extends JpaRepository<Office, String> {

}
