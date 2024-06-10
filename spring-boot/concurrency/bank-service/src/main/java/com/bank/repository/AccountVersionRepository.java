package com.bank.repository;

import com.bank.entity.AccountVersion;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountVersionRepository extends JpaRepository<AccountVersion, String> {

    boolean existsByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
    Optional<AccountVersion> findByUsername(String username);

}
