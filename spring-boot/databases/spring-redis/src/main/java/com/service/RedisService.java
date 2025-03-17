package com.service;

import com.model.Student;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final String HASH_KEY = "STUDENT";

    private final RedisTemplate<Object, Object> redisTemplate;
    private HashOperations<Object, String, Student> hashOperations;

//    @Autowired
//    public RedisService(RedisTemplate<Object, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        this.hashOperations = redisTemplate.opsForHash();
//    }

    public void save(Student student) {
        hashOperations.put(HASH_KEY, student.getId(), student);
    }

    public Map<String, Student> findAll() {
        return hashOperations.entries(HASH_KEY);
    }

    public Student findById(String id) {
        return hashOperations.get(HASH_KEY, id);
    }

    public void update(Student student) {
        save(student);
    }

    public void delete(String id) {
        hashOperations.delete(HASH_KEY, id);
    }

}
