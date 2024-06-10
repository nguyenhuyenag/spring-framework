package com.bank.controller;

import com.bank.dto.request.AtmRequest;
import com.bank.dto.response.AtmResponse;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/transfer")
    ResponseEntity<?> withdraw(@RequestBody AtmRequest request) {
        var response = accountService.withdraw(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/transfer-v2")
    ResponseEntity<?> withdrawV2(@RequestBody AtmRequest request) {
        AtmResponse response = new AtmResponse();
        try {
            response = accountService.withdrawVersion(request);
        } catch (Exception e) {
            response.setMessage(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(response);
    }

}
