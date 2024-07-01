package com.core.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
    - CountDownLatch & ExecutorService:

		 + Nếu CountDownLatch khởi tạo giá trị ban đầu là 3 thì bắt buộc phải countdown
		 về 0 thì main-thread mới thực thi. Khi hàm CountDownLatch.await() được gọi, các
		 thread sẽ bị chặn cho đến khi đếm đến 0. CountDownLatch thực sự hữu ích khi biết
		 chính xác số lượng Thread.

		 + Đối với Executor, ta có thể khởi tạo giá trị ban đầu là 4, queue là 20. Nếu
		 số lượng thread là 5 hay 10 thì nó có thể thêm vào queue để thực thi sau.
 */
public class CountDownLatch2 {

    private static class Worker implements Runnable {

        private final int delay;
        private final String name;
        private final CountDownLatch latch;

        public Worker(int delay, CountDownLatch latch, String name) {
            this.delay = delay;
            this.latch = latch;
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);  // Set the thread name here
            try {
                TimeUnit.SECONDS.sleep(delay);
                latch.countDown();
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e.getMessage());
            }
            System.out.println(Thread.currentThread().getName() + " -> finished");
        }
    }

    public static void main(String[] args) {
        System.out.println("Start main-thread, name=" + Thread.currentThread().getName());
        int count = 4;
        final CountDownLatch latch = new CountDownLatch(count);

        ExecutorService executor = Executors.newFixedThreadPool(count);

        for (int i = 0; i < count; i++) {
            executor.execute(new Worker(i, latch, "WORKER-" + (i + 1)));
        }

        executor.shutdown(); // No more tasks will be accepted

        try {
            if (!latch.await(30, TimeUnit.SECONDS)) {
                System.out.println("Timeout waiting for tasks to complete.");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted: " + e.getMessage());
        } finally {
            executor.shutdownNow(); // Cancel currently executing tasks
        }

        // Main thread has started
        System.out.println(Thread.currentThread().getName() + " -> finished");
    }
}
