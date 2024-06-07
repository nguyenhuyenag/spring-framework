package com.bank.controller;

import com.bank.dto.request.WithDrawRequest;
import com.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/atm")
    ResponseEntity<?> withdraw(@RequestBody WithDrawRequest request) {
        var response = accountService.withdraw(request);
        return ResponseEntity.ok(response);
    }

}
