package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/*-
    - By default spring events are synchronous.

    - Mặc định, Spring sử dụng SimpleAsyncTaskExecutor.

    - Nếu số lượng yêu listener lớn hơn số thread trong thread pool, các yêu cầu mới
    sẽ bị chặn lại, dẫn đến hiện tượng "nghẽn" (bottleneck).

    - Cần cấu hình phù hợp TaskExecutor để đảm bảo thread pool có đủ kích thước xử lý.
*/
@Configuration
public class AsynchronousSpringEventsConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }

    @Bean(name = "listenerTaskExecutor")
    public ThreadPoolTaskExecutor mailTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // Số thread chính
        executor.setMaxPoolSize(10);    // Số thread tối đa
        executor.setQueueCapacity(50);  // Dung lượng hàng đợi
        executor.setWaitForTasksToCompleteOnShutdown(true); // Đợi xử lý hết tác vụ trước khi shutdown
        executor.setAwaitTerminationSeconds(30);            // Thời gian chờ tối đa khi shutdown
        executor.setThreadNamePrefix("ListenerTaskExecutor-");
        executor.initialize();
        return executor;
    }

}
