package com.service;

import com.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {

    private static final String HASH_KEY = "STUDENT";

    private final RedisTemplate<Object, Object> redisTemplate;
    // private final StringRedisTemplate stringRedisTemplate;
    // private final RedisOperations<String, String> operations;
    private HashOperations<Object, Object, String> hashOperations;

    @PostConstruct
    private void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void add(Student student) {
        hashOperations.put(HASH_KEY, student.getId(), student.toString());
        System.out.println(student);
    }

//    public String findById(String studentId) {
//        return hashOperations.get(HASH_KEY, studentId);
//    }

    public List<String> findAll() {
        return hashOperations.values(HASH_KEY);
    }

    public Map<Object, String> getAll() {
        return hashOperations.entries(HASH_KEY);
    }

//    private HashOperations<Object, String, Student> hashOperations; // = redisTemplate.opsForHash();
//
//    @Autowired
//    public RedisService(RedisTemplate<Object, Object> redisTemplate,
//                        HashOperations<Object, String, Student> hashOperations) {
//        // this.redisTemplate = redisTemplate;
//        this.hashOperations = redisTemplate.opsForHash();
//    }

//    public void save(Student student) {
//        operations.
//        operations.put(HASH_KEY, student.getId(), student);
//    }
//
//    public Map<String, Student> findAll() {
//        return hashOperations.entries(HASH_KEY);
//    }
//
//    public Student findById(String id) {
//        return hashOperations.get(HASH_KEY, id);
//    }
//
//    public void update(Student student) {
//        save(student);
//    }
//
//    public void delete(String id) {
//        hashOperations.delete(HASH_KEY, id);
//    }

}
