package com.bank.service;

import com.bank.dto.request.WithDrawRequest;
import com.bank.entity.Account;
import com.bank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Map<String, Object> withdraw(WithDrawRequest request) {
        Map<String, Object> response = new HashMap<>();
        String username = request.getUsername();
        response.put("username", username);

        // Important, see AccountRepository.findByUsername()
        var accountOpt = accountRepository.findByUsername(username);

        if (accountOpt.isEmpty()) {
            response.put("message", "User not found");
            return response;
        }

        var account = accountOpt.get();

        if (account.getBalance() < request.getAmount()) {
            response.put("message", "The balance is not enough");
            return response;
        }

        account.setBalance(account.getBalance() - request.getAmount());
        var save = accountRepository.save(account);
        response.put("message", "Successful withdrawal " + request.getAmount());
        response.put("account_balance", save.getBalance());

        return response;
    }

}
