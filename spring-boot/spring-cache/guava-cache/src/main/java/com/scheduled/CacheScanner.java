//package com.scheduled;
//
//import com.google.common.cache.Cache;
//import com.model.CacheData;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.Map;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class CacheScanner {
//
//    private final Cache<String, CacheData> cacheManager;
//
//    @Scheduled(fixedRate = 3000) // Chạy mỗi 3 giây
//    public void scan() {
//        Map<String, CacheData> cacheMap = cacheManager.asMap();
//        System.out.println(cacheMap);
//        System.out.println();
//    }
//
//}
