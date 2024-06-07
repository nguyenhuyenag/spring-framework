package com.bank.configuration;

import com.bank.entity.Account;
import com.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

// https://viblo.asia/p/tai-sao-phai-dung-bigdecimal-khi-tinh-toan-ve-tien-te-trong-java-oOVlYqRnl8W
@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    @Bean
    public ApplicationRunner applicationRunner(AccountRepository accountRepository) {
        log.info("Initializing application.....");
        return args -> {
            Map<String, Integer> accounts = new HashMap<>();
            accounts.put("halinh", 10_000_000);
            accounts.put("minhanh", 20_000_000);
            accounts.put("nguyenvy", 30_000_000);
            accounts.forEach((username, balance) -> {
                if(!accountRepository.existsByUsername(username)) {
                    Account account = Account.builder()
                            .username(username)
                            .balance(balance)
                            .build();
                    accountRepository.save(account);
                }
            });
            log.info("Application initialization completed .....");
        };
    }
}
