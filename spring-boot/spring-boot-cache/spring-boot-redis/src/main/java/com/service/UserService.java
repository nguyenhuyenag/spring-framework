package com.service;

import com.model.User;
import com.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate; // cho Object

//    @Autowired
//    private ObjectMapper objectMapper;                 // Jackson

    private final UserRepository userRepository;

    private final String GROUP_USER_KEY = "users";
    public static final String GROUP_USER_LIST_KEY = "usersList";

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        System.out.println("Redis debug:");
        Set<String> keysAll = stringRedisTemplate.keys("*");
        System.out.println("Redis keys: " + keysAll);
        // → [users::1, users::2, products::5, ...]

        Set<String> keys = stringRedisTemplate.keys("users::*");  // ⚠️ chỉ dùng khi debug
        for (String key : keys) {
            String value = stringRedisTemplate.opsForValue().get(key);
            System.out.println(key + " = " + value);
        }
    }

    // CREATE, INSERT → @CachePut
//    @CachePut(value = GROUP_USER_KEY, key = "#result.id")
//    @CacheEvict(value = GROUP_USER_KEY, key = "'all'")   // ← xóa cache để findAll đúng
//    public User createUser(User user) {
//        // System.out.println("Insert user and put to cache: " + user);
//        return userRepository.save(user);
//    }
    @Caching(
            put = @CachePut(value = GROUP_USER_KEY, key = "#result.id"),
            evict = @CacheEvict(value = GROUP_USER_LIST_KEY, allEntries = true) // ← xóa cache để findAll đúng
    )
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // READ, SELECT → @Cacheable
    @Cacheable(value = GROUP_USER_KEY, key = "#id")
    public User getUserById(Long id) {
        System.out.println("Query DB cho user id: " + id);
        return userRepository.findById(id).orElse(null);
    }

    @Cacheable(value = GROUP_USER_LIST_KEY, key = "'all'") // Thực tế thì ít cache ở findAll, nên cache
    public List<User> findAll() {
        System.out.println("Query DB for all users");
        return userRepository.findAll();
    }

    // UPDATE → UPDATE → @CachePut
    @Caching(
            put = @CachePut(value = GROUP_USER_KEY, key = "#id"),
            evict = @CacheEvict(value = GROUP_USER_LIST_KEY, allEntries = true)  // ← xóa cache để findAll đúng
    )
    // @CachePut(value = GROUP_USER_KEY, key = "#id")  // ✅ key = tham số "id"
    public User updateUser(Long id, User userUpdate) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));

        user.setName(userUpdate.getName());
        user.setEmail(userUpdate.getEmail());  // ✅ sửa name → email

        return userRepository.save(user);
    }

    @CacheEvict(value = GROUP_USER_KEY, key = "#id") // Xóa cache findAll nếu có
    public void deleteUser(Long id) {
        System.out.println("Delete user and remove from cache: " + id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found: " + id);
        }
        userRepository.deleteById(id);
    }

}
