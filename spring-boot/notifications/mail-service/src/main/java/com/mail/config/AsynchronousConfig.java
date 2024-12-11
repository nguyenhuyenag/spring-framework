package com.mail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AsynchronousConfig {

    @Bean(name = "mailSenderTaskExecutor")
    public ThreadPoolTaskExecutor mailTaskExecutor() {
        // @formatter:off
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // Số thread chính
        executor.setMaxPoolSize(10);    // Số thread tối đa
        executor.setQueueCapacity(50);  // Dung lượng hàng đợi

        executor.setWaitForTasksToCompleteOnShutdown(true); // Đợi xử lý hết tác vụ trước khi shutdown
        executor.setAwaitTerminationSeconds(30);            // Thời gian chờ tối đa khi shutdown

        executor.setThreadNamePrefix("MailSenderTaskExecutor-");
        executor.initialize();
        // @formatter:on
        return executor;
    }

}
