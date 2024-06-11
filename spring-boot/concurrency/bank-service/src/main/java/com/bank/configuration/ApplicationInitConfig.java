package com.bank.configuration;

import com.bank.entity.Account;
import com.bank.entity.AccountVersion;
import com.bank.repository.AccountRepository;
import com.bank.repository.AccountVersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApplicationInitConfig {

    private final AccountRepository accountRepository;
    private final AccountVersionRepository accountVersionRepository;

    @Bean
    public ApplicationRunner applicationRunner() {
        log.info("Initializing application.....");
        return args -> {
            addAccount();
            addAccountV2();
            log.info("Application initialization completed .....");
        };
    }

    private void addAccount() {
        Map<String, Integer> accounts = new HashMap<>();
        accounts.put("halinh", 5_000_000);
        accounts.put("minhanh", 20_000_000);
        accounts.put("nguyenvy", 30_000_000);
        accounts.forEach((username, balance) -> {
            if (!accountRepository.existsByUsername(username)) {
                Account account = Account.builder()
                        .username(username)
                        .balance(balance)
                        .build();
                accountRepository.save(account);
            }
        });
    }

    private void addAccountV2() {
        Map<String, Integer> accounts = new HashMap<>();
        accounts.put("halinh", 5_000_000);
        accounts.put("minhanh", 20_000_000);
        accounts.put("nguyenvy", 30_000_000);
        accounts.forEach((username, balance) -> {
            if (!accountVersionRepository.existsByUsername(username)) {
                AccountVersion account = AccountVersion.builder()
                        .username(username)
                        .balance(balance)
                        .build();
                accountVersionRepository.save(account);
            }
        });
    }
}
