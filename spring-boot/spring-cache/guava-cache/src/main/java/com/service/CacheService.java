package com.service;

import com.google.common.cache.Cache;
import com.model.CacheData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CacheService {

    private final Cache<String, CacheData> cacheManager;

    public CacheData generateOtp() {
        String id = UUID.randomUUID().toString();
        int otp = Integer.parseInt(RandomStringUtils.randomNumeric(6));
        long expiredTime = Instant.now().plusSeconds(5).toEpochMilli();
        CacheData cache = new CacheData(id, otp, expiredTime);
        cacheManager.put(id, cache);
        System.out.println("Current Cache: ");
        System.out.println(cacheManager.asMap());
        return cache;
    }

    public CacheData getEmailData(String email) {
        return cacheManager.getIfPresent(email);
    }

    public void removeEmailData(String email) {
        cacheManager.invalidate(email);
    }

}
