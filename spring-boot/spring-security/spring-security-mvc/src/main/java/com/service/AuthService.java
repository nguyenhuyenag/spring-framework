package com.service;

import com.entity.History;
import com.repository.HistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final HistoryRepository historyRepository;

    public void parseToken(String tokenId) {
        Optional<History> byTokenId = historyRepository.findByTokenId(tokenId);
        if (byTokenId.isEmpty()) {
            log.error("TokenId={} not found", tokenId);
            return;
        }
    }

}
