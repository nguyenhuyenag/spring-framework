package com.service;

import com.model.CacheData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CacheService {

    // private final com.google.common.cache.Cache<String, CacheData> cacheManager;
    private final com.github.benmanes.caffeine.cache.Cache<String, CacheData> cacheManager;

    private final int EXPIRED_TIME = 5; //

    public CacheData generateOtp() {
        String id = UUID.randomUUID().toString();
        int otp = Integer.parseInt(RandomStringUtils.randomNumeric(6));

        long expiredAfter = TimeUnit.SECONDS.toMillis(EXPIRED_TIME);
        CacheData cache = new CacheData(id, otp, expiredAfter);

        cacheManager.put(id, cache);
        if (!cacheManager.asMap().isEmpty()) {
            System.out.println("Current Cache: ");
            System.out.println(cacheManager.asMap());
        }

        return cache;
    }

    public boolean verifyOtp(String id, int otp) {
        CacheData data = cacheManager.getIfPresent(id);
        if (data == null) {
            return false;
        }
        if (data.getOtp() == otp) {
            cacheManager.invalidate(id);
            return true;
        }
        return false;
    }

}
