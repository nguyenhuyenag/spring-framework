package com.bank.service;

import com.bank.dto.request.WithDrawRequest;
import com.bank.entity.Account;
import com.bank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Map<String, Object> withdraw(WithDrawRequest request) {
        Map<String, Object> response = new HashMap<>();
        String username = request.getUsername();
        response.put("username", username);
        var accountOpt = accountRepository.findByUsername(username);

        // User not found
        if (accountOpt.isEmpty()) {
            response.put("message", "User not found");
            return response;
        }

        var account = accountOpt.get();

        // Too poor
        if (account.getBalance() < request.getAmount()) {
            response.put("message", "The balance is not enough");
            return response;
        }

        // OK go go
        account.setBalance(account.getBalance() - request.getAmount());
        var save = accountRepository.save(account);
        response.put("message", "Successful withdrawal " + request.getAmount());
        response.put("account_balance", save.getBalance());
        return response;
    }
}
