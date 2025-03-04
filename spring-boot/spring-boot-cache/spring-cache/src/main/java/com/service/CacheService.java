package com.service;

import com.config.CaffeineCacheConfig;
import com.model.CacheData;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
// @RequiredArgsConstructor
public class CacheService {

    // private final com.google.common.cache.Cache<String, CacheData> cacheManager;
    @Autowired
    @Qualifier("otpCache")
    private com.github.benmanes.caffeine.cache.Cache<String, CacheData> cacheManager;

    private static final int EXPIRED_TIME = CaffeineCacheConfig.EXPIRED_TIME;
    private static final TimeUnit EXPIRED_TIME_UNIT = CaffeineCacheConfig.EXPIRED_TIME_UNIT;

    public CacheData generateOtp() {
        String id = UUID.randomUUID().toString();
        int otp = Integer.parseInt(RandomStringUtils.randomNumeric(6));

        long expiredAfter = EXPIRED_TIME_UNIT.toMillis(EXPIRED_TIME);
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
