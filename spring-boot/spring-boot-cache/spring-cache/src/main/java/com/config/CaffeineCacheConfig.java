package com.config;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.model.CacheData;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/*
    Hàm RemovalListener không chạy định kỳ mà chỉ được kích hoạt khi một phần tử bị xóa khỏi cache.
    Thời điểm RemovalListener chạy phụ thuộc vào các sự kiện xóa phần tử khỏi cache, chẳng hạn như:

    - Xóa thủ công: Khi bạn gọi cache.invalidate(key) hoặc cache.invalidateAll().
    - Hết hạn: Khi phần tử hết hạn dựa trên cấu hình expireAfterWrite hoặc expireAfterAccess.
    - Ghi đè: Khi một giá trị mới được thêm vào cache với cùng một khóa.
    - Vượt quá kích thước cache: Khi số lượng phần tử trong cache vượt quá maximumSize,
    các phần tử cũ sẽ bị xóa để nhường chỗ cho phần tử mới.
 */
@Configuration
public class CaffeineCacheConfig {

    @Bean
    public Cache<String, CacheData> otpCache() {
        return Caffeine.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .removalListener((key, value, cause) -> {
                    try {
                        System.out.println(LocalDateTime.now() + "Key: " + key + " removed. Cause: " + cause);
                    } catch (Exception e) {
                        System.err.println(LocalDateTime.now() + "Error in removal listener: " + e.getMessage());
                    }
                }).build();
    }

}
