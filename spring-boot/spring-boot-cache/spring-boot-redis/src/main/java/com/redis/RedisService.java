package com.redis;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class RedisService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate; // cho Object

    @Autowired
    private ObjectMapper objectMapper;                 // Jackson

    // ============================================
    // 1.1 PUT number, string
    // ============================================
    public void redisForString() {
        // TODO: Set
        // Không có thời hạn
        stringRedisTemplate.opsForValue().set("numbers:num1", "123");
        stringRedisTemplate.opsForValue().set("numbers:num2", "456");

        stringRedisTemplate.opsForValue().set("strings:hi1", "hello redis");
        stringRedisTemplate.opsForValue().set("strings:hi2", "hello redis again");

        // chỉ set nếu key tồn tại
        stringRedisTemplate.opsForValue().setIfPresent("strings:hi2", "hello redis again 2");
        // chỉ set nếu key CHƯA tồn tại
        stringRedisTemplate.opsForValue().setIfAbsent("strings:hi3", "hello redis again 3");

        // Có TTL — tự hết hạn sau 10 phút
        stringRedisTemplate.opsForValue().set("tokens:token1", "abc123", Duration.ofMinutes(10));
        stringRedisTemplate.opsForValue().set("tokens:token2", "xyz789", Duration.ofMinutes(10));

        // TODO: Get
        String number = stringRedisTemplate.opsForValue().get("numbers:num1");
        System.out.println("num1 = " + number);

        String str = stringRedisTemplate.opsForValue().get("strings:hi1");
        System.out.println("hi1 = " + str);

        String token = stringRedisTemplate.opsForValue().get("tokens:token1");
        System.out.println("token1 = " + token);

        // Print all keys
        Set<String> keys = stringRedisTemplate.keys("*");
        System.out.println("Redis keys: " + keys);
    }

    // ============================================
    // 2.1 PUT object, json, list
    // ============================================
    public void putObjectJsonList() throws Exception {
        // --- Object ---
        User user = new User();
        user.setId(1L);
        user.setName("alice");
        user.setEmail("alice@example.com");
        redisTemplate.opsForValue().set("user:1", user);

        // --- JSON string ---
        String userJson = objectMapper.writeValueAsString(user);
        stringRedisTemplate.opsForValue().set("user:1:json", userJson);

        // --- List<User> ---
        User user2 = new User();
        user2.setId(2L);
        user2.setName("bob");
        user2.setEmail("bob@example.com");

        List<User> users = Arrays.asList(user, user2);
        redisTemplate.opsForValue().set("users:list", users);

        // --- List<User> dạng JSON ---
        String usersJson = objectMapper.writeValueAsString(users);
        redisTemplate.opsForValue().set("users:list:json", usersJson);

        System.out.println("✅ [2.1] PUT object, json, list OK");
    }

    // ============================================
    // 2.2 READ object, json, list
    // ============================================
    public void readObjectJsonList() throws Exception {

        // --- Object ---
        User cachedUser = (User) redisTemplate.opsForValue().get("user:1");
        System.out.println("user = " + cachedUser);

        // --- JSON string → Object ---
        String cachedJson = stringRedisTemplate.opsForValue().get("user:1:json");
        User userFromJson = objectMapper.readValue(cachedJson, User.class);
        System.out.println("userFromJson = " + userFromJson.getName());

        // --- List<User> ---
        @SuppressWarnings("unchecked")
        List<User> cachedUsers = (List<User>) redisTemplate.opsForValue().get("users:list");
        System.out.println("users count = " + cachedUsers.size());

        // --- List<User> từ JSON ---
        String cachedUsersJson = stringRedisTemplate.opsForValue().get("users:list:json");
        List<User> usersFromJson = objectMapper.readValue(
                cachedUsersJson,
                new TypeReference<>() {
                }
        );
        System.out.println("usersFromJson count = " + usersFromJson.size());
    }

}
