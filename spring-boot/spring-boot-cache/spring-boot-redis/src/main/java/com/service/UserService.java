package com.service;

import com.model.User;
import com.redis.RedisReaderService;
import com.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private CacheManager cacheManager;


    private final UserRepository userRepository;
    private final RedisReaderService redisReaderService;

    private final String GROUP_USER_KEY = "users";

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
//        Set<String> keys = stringRedisTemplate.keys("users::*");  // ⚠️ chỉ dùng khi debug
//        if (keys != null) {
//            for (String key : keys) {
//                String value = stringRedisTemplate.opsForValue().get(key);
//                System.out.println(key + " = " + value);
//            }
//        }
        cacheManager.getCacheNames().forEach(name -> {
            System.out.println("Cache name: " + name);
        });
    }

    // CREATE → INSERT → @CachePut
    @CachePut(value = GROUP_USER_KEY, key = "#result.id")
    public User createUser(User user) {
        System.out.println("Insert user and put to cache: " + user);
        return userRepository.save(user);
    }

    // READ → SELECT → @Cacheable
    @Cacheable(value = GROUP_USER_KEY, key = "#id")
    public User getUserById(Long id) {
        System.out.println("Query DB cho user id: " + id);
        return userRepository.findById(id).orElse(null);
    }

    // UPDATE → UPDATE → @CachePut
    @CachePut(value = GROUP_USER_KEY, key = "#user.id")
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // DELETE → DELETE → @CacheEvict
    @CacheEvict(value = GROUP_USER_KEY, key = "#id")
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Cacheable(value = GROUP_USER_KEY)
    public java.util.List<User> findAll() {
        return userRepository.findAll();
    }

}
