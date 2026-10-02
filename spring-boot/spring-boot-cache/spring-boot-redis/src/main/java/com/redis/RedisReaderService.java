package com.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisReaderService {

    private final StringRedisTemplate stringRedisTemplate;

    // Đọc một key dạng String (thường dùng cho cache)
    public String getRawValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);  // [citation:6][citation:12]
    }

    // Đọc hash
    public Map<Object, Object> getHash(String key) {
        return stringRedisTemplate.opsForHash().entries(key);
    }

}
