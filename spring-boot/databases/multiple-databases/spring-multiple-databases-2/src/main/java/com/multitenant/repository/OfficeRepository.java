package com.multitenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multitenant.domain.Office;

public interface OfficeRepository extends JpaRepository<Office, String> {

}
