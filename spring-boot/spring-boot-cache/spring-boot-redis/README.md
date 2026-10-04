# Spring Boot Redis

# Syntax

        -p <cổng_host>:<cổng_container> 
      
    => Khi có kết nối vào cổng 3307 trên máy Windows, sẽ chuyển tiếp (forward) vào cổng 3306 
        bên trong container là cổng mà MySQL đang sử dụng..

## Install MySQL

    docker run -d \
        --name mysql-8.0.36 \
        -e MYSQL_USER=huyennv \
        -e MYSQL_PASSWORD=root \
        -e MYSQL_ROOT_PASSWORD=root \
        -p 3306:3306 \
        mysql:8.0.36-debian

## Install Redis

    docker pull redis:7.4
    docker run -d --name <container_name> -p 6379:6379 <image_id>

    CLI:
        docker exec -it <container_name> redis-cli
        > ping

## Spring Boot Redis

    # Ánh xạ CRUD → Annotation Cache
    
        --------------------------------------------------------------------
        |  Thao tác  |     SQL      |      Annotation Spring Cache        
        --------------------------------------------------------------------
        |   CREATE   |   INSERT     |           @CachePut                 
        --------------------------------------------------------------------
        |   READ     |   SELECT     |           @Cacheable           
        --------------------------------------------------------------------
        |   UPDATE   |   UPDATE     |           @CachePut                 
        --------------------------------------------------------------------
        |   DELETE   |   DELETE     |           @CacheEvict               
        --------------------------------------------------------------------

    @Cacheable(
        value = "users",
        key = "#id",
        condition = "#id > 0",
        unless = "#result == null",
        sync = false,
        cacheManager = "cacheManager",
        cacheNames = {"users", "usersBackup"}
    )

    @CachePut(value = "users", key = "#id")            // key = tham số id
    public User findById(Long id) {
    }

    @CachePut(value = "users", key = "#user.id")       // key = #user.id, #user.email
    public User findById(User user) {
    }

    @CachePut(value = "users", key = "#result.id")     // result có sẵn sau khi method chạy xong
    public User findById(User user) {
        return <to_do_something>;
    }

    // Xóa TOÀN BỘ cache của users (khi cần refresh)
    @CacheEvict(value = "users", allEntries = true)
    public void clearAllUsersCache() { }
    => Chỉ xóa nhóm users, không đụng đến products hay orders

## Rate Limiting

    Cần rate limit?
        │
        ├─ Chỉ cần đơn giản, chấp nhận burst biên?
        │  └─► Fixed Window (INCR + EXPIRE)
        │
        ├─ Cần chính xác cao, bộ nhớ không thành vấn đề?
        │  └─► Sliding Window Log (ZSet)
        │
        ├─ Cần chính xác cao, tiết kiệm bộ nhớ?
        │  └─► Sliding Window Counter
        │
        ├─ Cần cho phép burst (API thực tế)?
        │  └─► Token Bucket
        │
        ├─ Cần làm mượt traffic (bảo vệ backend)?
        │  └─► Leaky Bucket
        │
        ├─ Cần giới hạn số request đồng thời?
        │  └─► Concurrent Limiter
        │
        └─ Cần tự điều chỉnh theo tải?
        └─► Adaptive Rate Limiting

