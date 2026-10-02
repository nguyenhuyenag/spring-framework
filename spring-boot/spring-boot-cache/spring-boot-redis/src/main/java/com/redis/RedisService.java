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

    public void redisForCollections() {
        // Thêm phần tử
        stringRedisTemplate.opsForList().rightPushAll("my:list", "a", "b", "c");
        // Đếm
        Long size1 = stringRedisTemplate.opsForList().size("my:list");
        System.out.println(size1);   // 3

        stringRedisTemplate.opsForSet().add("my:set", "a", "b", "c", "a");   // "a" trùng → bỏ
        Long size2 = stringRedisTemplate.opsForSet().size("my:set");
        System.out.println(size2);   // 3 (không phải 4)

        // ZSet = Sorted Set: Mỗi phần tử có thêm một score để sắp xếp tự động
        /*
                Method	                            Tác dụng
                add(key, value, score)	            Thêm phần tử
                incrementScore(key, value, delta)	Cộng thêm score
                size(key) / zCard(key)	            Đếm số phần tử
                range(key, start, end)	            Lấy theo khoảng (tăng dần)
                reverseRange(key, start, end)	    Lấy theo khoảng (giảm dần)
                rangeByScore(key, min, max)	        Lấy theo khoảng score
                rank(key, value)	                Xếp hạng (tăng dần, 0-based)
                reverseRank(key, value)	            Xếp hạng (giảm dần)
                remove(key, values...)	            Xóa phần tử
                removeRangeByScore(key, min, max)	Xóa theo khoảng score

            Ứng dụng:
                // 1. 🏆 Bảng xếp hạng game
                // 2. 🔥 Trending / Hot news3.
                // 3. ⏰ Rate limiting (giới hạn tần suất)
                // 4. 📅 Sắp xếp task theo thời gian
                // 5. 🎯 Gợi ý sản phẩm (recommendation)
         */
        stringRedisTemplate.opsForZSet().add("my:zset", "a", 1.0);
        stringRedisTemplate.opsForZSet().add("my:zset", "a", 2.0);
        stringRedisTemplate.opsForZSet().add("my:zset", "b", 2.0);

        Long size3 = stringRedisTemplate.opsForZSet().size("my:zset");
        System.out.println(size3);   // 2

        stringRedisTemplate.opsForHash().put("my:hash", "name", "alice");
        stringRedisTemplate.opsForHash().put("my:hash", "email", "alice@example.com");

        Long size4 = stringRedisTemplate.opsForHash().size("my:hash");
        System.out.println(size4);   // 2 (2 field)
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
