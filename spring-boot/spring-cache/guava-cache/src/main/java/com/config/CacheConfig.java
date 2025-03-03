package com.config;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.model.CacheData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public Cache<String, CacheData> cacheData() {
        return CacheBuilder.newBuilder()
                .maximumSize(1000) // Số lượng tối đa các entry trong cache
                .expireAfterWrite(10, TimeUnit.SECONDS) // Tự động xóa sau khoảng thời gian
                .removalListener((RemovalNotification<String, CacheData> notification) -> {
                    System.out.printf("Removed cache for id: %s, Reason: %s%n",
                            notification.getKey(), notification.getCause());
                })
                .build();
    }

}
